package mainProject;

import entities.BusDriver;
import entities.Passenger;
import entities.Porter;
import stubs.*;

import java.util.Random;

import static mainProject.SimulPar.LANDINGS;
import static mainProject.SimulPar.PASSENGERS;

public class AirportVClientServer {

    public static void main(String args[]) {
        try {

            /**
             * Random generation of passenger info for simulation purposes only
             * Luggage in the plane hold, luggage lost and final destination (yes/no)
             * **/

            int[][] passengersLuggage = new int[LANDINGS][PASSENGERS];
            boolean[][] passengersFinalDestination = new boolean[LANDINGS][PASSENGERS];

            for (int i = 0; i < LANDINGS; i++) {
                for (int j = 0; j < PASSENGERS; j++) {
                    passengersLuggage[i][j] = new Random().nextInt(SimulPar.LUGGAGE + 1);
                    passengersFinalDestination[i][j] = (Math.random() < 0.5);
                }
            }

            // Random generation of luggage LOST for each passenger (for simulation purposes)
            // only for passengers with final destination
            int[][] plainHoldLuggage = new int[LANDINGS][PASSENGERS];

            for (int i = 0; i < LANDINGS; i++) {
                for (int j = 0; j < PASSENGERS; j++) {
                    if (passengersFinalDestination[i][j]) {
                        plainHoldLuggage[i][j] = new Random().nextInt(passengersLuggage[i][j]/2+1);
                    } else {
                        plainHoldLuggage[i][j] = passengersLuggage[i][j];
                    }
                }
            }

            for (int i = 0; i < LANDINGS; i++) {
                for (int j = 0; j < PASSENGERS; j++) {
                    System.out.println(String.format("%d\t%d\t%d\t%d\t%b",i,j,passengersLuggage[i][j],plainHoldLuggage[i][j],passengersFinalDestination[i][j]));
                }
            }

            RepositoryStub repositoryStub = new RepositoryStub();

            ArrivalLoungeStub arrivalLoungeStub = new ArrivalLoungeStub(SharedRegionConfig.SERVER_ARRIVAL_LOUNGE_HOSTNAME,
                                                                        SharedRegionConfig.SERVER_ARRIVAL_LOUNGE_PORT);

            ArrivalTerminalTransferQuayStub arrivalTerminalTransferQuayStub = new ArrivalTerminalTransferQuayStub(SharedRegionConfig.SERVER_ARRIVAL_TERMINAL_TRANSFER_QUAY_HOSTNAME,
                                                                                                                  SharedRegionConfig.SERVER_ARRIVAL_TERMINAL_TRANSFER_QUAY_PORT);

            ArrivalTerminalExitStub arrivalTerminalExitStub = new ArrivalTerminalExitStub(SharedRegionConfig.SERVER_ARRIVAL_TERMINAL_EXIT_HOSTNAME,
                                                                                          SharedRegionConfig.SERVER_ARRIVAL_TERMINAL_EXIT_PORT);

            DepartureTerminalTransferQuayStub departureTerminalTransferQuayStub = new DepartureTerminalTransferQuayStub(SharedRegionConfig.SERVER_DEPARTURE_TERMINAL_TRANSFER_QUAY_HOSTNAME,
                                                                                                                        SharedRegionConfig.SERVER_DEPARTURE_TERMINAL_TRANSFER_QUAY_PORT);

            DepartureTerminalEntranceStub departureTerminalEntranceStub = new DepartureTerminalEntranceStub(SharedRegionConfig.SERVER_DEPARTURE_TERMINAL_ENTRANCE_HOSTNAME,
                                                                                                            SharedRegionConfig.SERVER_DEPARTURE_TERMINAL_ENTRANCE_PORT);

            BaggageCollectionPointStub baggageCollectionPointStub = new BaggageCollectionPointStub(SharedRegionConfig.SERVER_BAGGAGE_COLLECTION_POINT_HOSTNAME,
                                                                                                   SharedRegionConfig.SERVER_BAGGAGE_COLLECTION_POINT_PORT);

            BaggageReclaimOfficeStub baggageReclaimOfficeStub = new BaggageReclaimOfficeStub(SharedRegionConfig.SERVER_BAGGAGE_RECLAIM_OFFICE_HOSTNAME,
                                                                                             SharedRegionConfig.SERVER_BAGGAGE_RECLAIM_OFFICE_PORT);

            TemporaryStorageAreaStub temporaryStorageAreaStub = new TemporaryStorageAreaStub(SharedRegionConfig.SERVER_TEMPORRY_STORAGE_AREA_HOSTNAME,
                                                                                             SharedRegionConfig.SERVER_TEMPORRY_STORAGE_AREA_PORT);
            /**
             * Entities
             **/
            Porter porter = new Porter(arrivalLoungeStub, temporaryStorageAreaStub, baggageCollectionPointStub);
            BusDriver busDriver = new BusDriver(arrivalTerminalTransferQuayStub, departureTerminalTransferQuayStub);
            Passenger[] passengers = new Passenger[PASSENGERS];

            porter.start();
            busDriver.start();

            for (int flightNumber = 0; flightNumber < LANDINGS; flightNumber ++) {

                repositoryStub.init_repository(flightNumber);                                                         //Reset flights info
                arrivalLoungeStub.init_plane_hold(flightNumber, plainHoldLuggage, passengersFinalDestination);        //Create the plane hold (simulation)
                arrivalTerminalExitStub.clean_up();
                departureTerminalEntranceStub.clean_up();
                baggageCollectionPointStub.clean_up();
                busDriver.setPassengersInTheBus(0);

                for (int j = 0; j < PASSENGERS; j ++) {
                    passengers[j]=new Passenger(j,
                                                passengersLuggage[flightNumber][j],
                                                passengersFinalDestination[flightNumber][j],
                                                arrivalLoungeStub,
                                                arrivalTerminalTransferQuayStub,
                                                arrivalTerminalExitStub,
                                                departureTerminalTransferQuayStub,
                                                departureTerminalEntranceStub,
                                                baggageCollectionPointStub,
                                                baggageReclaimOfficeStub);
                    passengers[j].start();
                }
                for (int j = 0; j < PASSENGERS; j ++) {
                    try{
                        passengers[j].join();
                    } catch(InterruptedException e){}
                }
            }

            porter.setKeepAlive(false);
            busDriver.setKeepAlive(false);

            arrivalLoungeStub.setPorterEndOfWork();
            arrivalTerminalTransferQuayStub.setBusDriverEndOfWork();

            porter.join();
            busDriver.join();

            /**** Shutdown dos servers **/
            arrivalLoungeStub.setSimulationFinished();
            arrivalTerminalTransferQuayStub.setSimulationFinished();
            arrivalTerminalExitStub.setSimulationFinished();
            departureTerminalTransferQuayStub.setSimulationFinished();
            departureTerminalEntranceStub.setSimulationFinished();
            baggageCollectionPointStub.setSimulationFinished();
            baggageReclaimOfficeStub.setSimulationFinished();
            temporaryStorageAreaStub.setSimulationFinished();
            repositoryStub.setSimulationFinished();

        }  catch (Exception ex) { System.out.println(ex); }

        }



}

