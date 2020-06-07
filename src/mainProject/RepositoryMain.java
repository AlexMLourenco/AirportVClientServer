/**
 * <h1>Repository Main</h1>
 * Launches the the Repository server
 *
 */

package mainProject;

import commonInfra.ServerCom;
import commonInfra.ServiceProvider;
import proxies.RepositoryProxy;
import sharedRegions.Repository;

import java.io.FileNotFoundException;
import java.net.SocketTimeoutException;

public class RepositoryMain {
    /**
     * Repository main's thread
     * @param args unused main args
     * @throws FileNotFoundException if the file isn't found
     * @see SocketTimeoutException
     */

    public static void main(String args[]) throws FileNotFoundException {
        ServerCom serverCom, serverConn;
        ServiceProvider serviceProvider;

        Repository repository = new Repository();
        RepositoryProxy repositoryProxy = new RepositoryProxy(repository);

        serverCom = new ServerCom(SharedRegionConfig.SERVER_REPOSITORY_PORT,0);
        serverCom.start();

        while(!repositoryProxy.simulationFinished()) {
            try {
                serverConn = serverCom.accept();
                serviceProvider = new ServiceProvider(repositoryProxy, serverConn);
                serviceProvider.start();
            } catch (SocketTimeoutException e) {
            }
        }
    }
}
