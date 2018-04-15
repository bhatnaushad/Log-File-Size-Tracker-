/**
 * @author Mohammad Naushad Bhat
 * @version 1 15/04/2018
 */

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
This is the only class used by this program.
 */
public class LogKeeper implements Runnable {

    /**
     * This function checks the operating System type. This function is needed to make the program platform independent.
     * This program however supports only Windows, Mac , unix and Linux Operating Systems
     *
     * @return  It returns the suitable path based on the Operating System
     */
    public static String checkOS() {
        String store = "";
        String OS = System.getProperty("os.name").toLowerCase();
        if(OS.indexOf("win") >= 0){
            store = "C:/Users/NAUSHAD/Desktop/";
        } else if(OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 ){
            store = "/home/";
        } else  if(OS.indexOf("mac") >= 0){
            store = "/home/";
        } else{
            return null;
        }
        return store;
    }

        //In Runnable Class there is a method run(). So here, we override this method.
        @Override
        public void run(){

            while(true){
                try {

                    //The file ,size of which is to be saved into another file (a.txt)
                    File file = new File(checkOS() + "test.txt");
                    float store = file.length();
                    store = store/1024 ;

                    /*
                    * Here FileOutputStream takes two arguments.
                    *   One is the location of the file (b.txt) in which the log is to be written
                    *  And another is boolean. i.e; true meaning that is data in the file won't be overewritten.
                    */
                    FileOutputStream out = new FileOutputStream(checkOS() + "SaveSize.txt", true);
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

                    //The lines below prints the length of file in kb on screen and the time on screen
                    System.out.println("File length: " + store + " KB" );
                    System.out.println("At time: " + simpleDateFormat.format(date));

                    //Here we store the data which is to be written into file b.txt into variable temp of String type
                    String temp = "At " + simpleDateFormat.format(date).toString() + ". The size of file is -> " + String.valueOf(store) + " KB \n";
                    //Now we convert String to byte
                    byte[] log = temp.getBytes();

                    //We write the data into file
                    out.write(log);

                } catch(Exception ex) {
                    ex.printStackTrace();
                }

                try {
                    /*
                        The program needs to write the size of file into another file after every 10 seconds
                        So, we make the thread sleep for 10 seconds (10000 ms).
                        It will repeate as it is in while loop and write the size every 10 seconds
                     */
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }


    /**
     * This is the main() method of the program. It makes the program work.
     * @param args In case command line arguments are required
     */
    public static void main(String []args){

            Thread t = new Thread(new LogKeeper());
            t.start();// Thread started
        }

    }
