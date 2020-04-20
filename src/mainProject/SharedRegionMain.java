package mainProject;

import common.ServerCom;
import common.ServiceProvider;
import proxies.SharedRegionProxyInterface;
import sharedRegions.SharedRegionInterface;
import stubs.RepositoryStub;

import java.lang.reflect.Constructor;
import java.net.SocketTimeoutException;

public class SharedRegionMain {

    public static void main(String args[]) {
        ServerCom serverCom, serverConn;
        ServiceProvider serviceProvider;
        try {
            String regionName = args[0];

            //Load by reflection the shared memory
            Class<?> shRegClass = Class.forName("sharedRegions." + regionName);
            Constructor<?> shRegConstructor = shRegClass.getConstructor(RepositoryStub.class);
            SharedRegionInterface sharedRegion = (SharedRegionInterface) shRegConstructor.newInstance(
                    new RepositoryStub(SimulPar.SERVER_REPOSITORY_HOSTNAME, SimulPar.SERVER_REPOSITORY_PORT));


            Class<?> proxyClass = Class.forName("proxies." + regionName + "Proxy");
            Constructor<?> proxyConstructor = proxyClass.getConstructor(shRegClass);
            SharedRegionProxyInterface proxy = (SharedRegionProxyInterface) proxyConstructor.newInstance(sharedRegion);

            serverCom = new ServerCom(SimulPar.getPortForSharedRegion(regionName),1000);
            serverCom.start();

            while(!proxy.simulationFinished()) {
                try {
                    serverConn = serverCom.accept();
                    serviceProvider = new ServiceProvider(proxy, serverConn);
                    serviceProvider.start();
                } catch (SocketTimeoutException e) {}
            }
            System.out.printf("%s: Bye!\n",regionName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
