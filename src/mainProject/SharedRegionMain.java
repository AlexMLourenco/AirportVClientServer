/**
 * <h1>SharedRegionMain</h1>
 * The SharedRegionMain class sets all the simulation variables
 * of the shared reagions to launch shared region server
 *
 */


package mainProject;

import commonInfra.ServerCom;
import commonInfra.ServiceProvider;
import proxies.SharedRegionProxyInterface;
import sharedRegions.SharedRegionInterface;
import stubs.ArrivalTerminalExitStub;
import stubs.DepartureTerminalEntranceStub;
import stubs.RepositoryStub;

import java.lang.reflect.Constructor;
import java.net.SocketTimeoutException;

public class SharedRegionMain {
    /**
     * Shared regions main's thread
     * @see SocketTimeoutException
     */

    public static void main(String args[]) {
        ServerCom serverCom, serverConn;
        ServiceProvider serviceProvider;
        try {
            String regionName = args[0];

            System.out.println("Starting Region Name: " + regionName);
            //Load by reflection the shared memory
            Class<?> shRegClass = Class.forName("sharedRegions." + regionName);

            Constructor<?> shRegConstructor = null;
            SharedRegionInterface sharedRegion = null;
            if (regionName.equalsIgnoreCase("ArrivalTerminalExit")) {
                shRegConstructor = shRegClass.getConstructor(RepositoryStub.class, DepartureTerminalEntranceStub.class);
                sharedRegion = (SharedRegionInterface) shRegConstructor.newInstance(
                        new RepositoryStub(SharedRegionConfig.SERVER_REPOSITORY_HOSTNAME, SharedRegionConfig.SERVER_REPOSITORY_PORT),
                        new DepartureTerminalEntranceStub(SharedRegionConfig.SERVER_DEPARTURE_TERMINAL_ENTRANCE_HOSTNAME, SharedRegionConfig.SERVER_DEPARTURE_TERMINAL_ENTRANCE_PORT));
            } else if (regionName.equalsIgnoreCase("DepartureTerminalEntrance")) {
                shRegConstructor = shRegClass.getConstructor(RepositoryStub.class, ArrivalTerminalExitStub.class);
                sharedRegion = (SharedRegionInterface) shRegConstructor.newInstance(
                        new RepositoryStub(SharedRegionConfig.SERVER_REPOSITORY_HOSTNAME, SharedRegionConfig.SERVER_REPOSITORY_PORT),
                        new ArrivalTerminalExitStub(SharedRegionConfig.SERVER_ARRIVAL_TERMINAL_EXIT_HOSTNAME, SharedRegionConfig.SERVER_ARRIVAL_TERMINAL_EXIT_PORT));
            } else {
                shRegConstructor = shRegClass.getConstructor(RepositoryStub.class);
                sharedRegion = (SharedRegionInterface) shRegConstructor.newInstance(
                        new RepositoryStub(SharedRegionConfig.SERVER_REPOSITORY_HOSTNAME, SharedRegionConfig.SERVER_REPOSITORY_PORT));
            }

            Class<?> proxyClass = Class.forName("proxies." + regionName + "Proxy");
            Constructor<?> proxyConstructor = proxyClass.getConstructor(shRegClass);
            SharedRegionProxyInterface proxy = (SharedRegionProxyInterface) proxyConstructor.newInstance(sharedRegion);

            serverCom = new ServerCom(SharedRegionConfig.getPortForSharedRegion(regionName),1000);
            serverCom.start();

            while(!proxy.simulationFinished()) {
                try {
                    serverConn = serverCom.accept();
                    serviceProvider = new ServiceProvider(proxy, serverConn);
                    serviceProvider.start();
                } catch (SocketTimeoutException e) {}
            }
            //System.out.printf("%s: Bye!\n",regionName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
