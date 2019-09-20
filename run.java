//import java.io.File;
import java.lang.management.*;
//import java.lang.management.ManagementFactory;
import java.io.*;
//import java.io.IOException;



// NOT WORKING:

//     class MProcessor 
// {
//             public MProcessor() 
//         {
//             String s;
//             try 
//             {
//                 Process ps = Runtime.getRuntime().exec("Pc.bat");
//                 BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream())); // find how to run this as admin and then ur cpu works.!!
//                 while((s = br.readLine()) != null) 
//                 {
//                     System.out.println(s);
//                 }
//             }
//             catch( Exception ex ) 
//             {
//                 System.out.println(ex.toString());
//             }
//         }
// }

//WORKING: shows everything
    class WindowsSystemInformation
    {
        int mb = 1024*1024;
        int gb = 1024*1024*1024;
        
        double get_tram() throws IOException 
        {            
            // either java pckg or cmd cli
            //com.sun.management.OperatingSystemMXBean operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();

            //RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
            //operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

            com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean)

            java.lang.management.ManagementFactory.getOperatingSystemMXBean();
            double physicalMemorySize = os.getTotalPhysicalMemorySize();

            physicalMemorySize=physicalMemorySize/gb;

            return physicalMemorySize;




            
            //System.out.println("Total Physical Memory : " + physicalMemorySize / gb + "GB ");

            // return physicalMemorySize;
           
            // Runtime runtime = Runtime.getRuntime();
            // Process process = runtime.exec("wmic ComputerSystem get TotalPhysicalMemory /format:value");        

            // BufferedReader systemInformationReader = new BufferedReader( new InputStreamReader( process.getInputStream() ) );            
    
            // StringBuilder stringBuilder = new StringBuilder();
            
            // String line;
    
            // while ((line = systemInformationReader.readLine()) != null)
            // {
            //     stringBuilder.append(line);
            //     stringBuilder.append(System.lineSeparator());
                
            // }    
            // String a;
            // a=stringBuilder.toString().trim();
            // return a;
        }

        double get_aram() throws IOException
        {
            // either java pckg or cmd cli
            //com.sun.management.OperatingSystemMXBean operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();

            //RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
            //operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

            com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean)

            java.lang.management.ManagementFactory.getOperatingSystemMXBean();
            
            double physicalfreeMemorySize = os.getFreePhysicalMemorySize();

            physicalfreeMemorySize=physicalfreeMemorySize/gb;            

            return physicalfreeMemorySize;
            
            
            


            // Runtime runtime = Runtime.getRuntime();
            // Process process = runtime.exec("wmic OS get FreePhysicalMemory /format:value");                 

            // BufferedReader systemInformationReader = new BufferedReader( new InputStreamReader( process.getInputStream() ) );
               
            // StringBuilder stringBuilder = new StringBuilder();
            
            // String line;
    
            // while ((line = systemInformationReader.readLine()) != null)
            // {
            //     stringBuilder.append(line);
            //     stringBuilder.append(System.lineSeparator());
                
            // }
            
            // String a;
            // a=stringBuilder.toString().trim();
            // return a;            
        }

        String get_cpu() throws IOException
        {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("wmic cpu get loadpercentage /format:value");     /*--> try to use this : c:\Windows\system32\typeperf "\processor(_total)\% processor time" */            
            //Process process = runtime.exec("wmic cpu get loadpercentage /every:5");
            
            BufferedReader systemInformationReader = new BufferedReader( new InputStreamReader( process.getInputStream() ) );
               
            StringBuilder stringBuilder = new StringBuilder();
            
            String line;
    
            while ((line = systemInformationReader.readLine()) != null)
            {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                
            }
            
            String a;
            a=stringBuilder.toString().trim();
            return a;            
        }

        double get_hdd() throws IOException
        {
            File diskPartition = new File("C:");
            
            //double totalCapacity = diskPartition.getTotalSpace() / gb;
            
            double freePartitionSpace = diskPartition.getFreeSpace() / gb;
            
            //double usablePartitionSpace = diskPartition.getUsableSpace() / gb; // ask sir which??

            // System.out.println("\n**** Sizes in Giga Bytes ****\n");

            // System.out.println("DISC SPACE DETAILS \n");

            // System.out.println("Total C partition size : " + totalCapacity + "GB");
            // System.out.println("Usable Space : " + usablePatitionSpace + "GB");

            // System.out.println("Free Space in drive C: : " + freePartitionSpace + "GB"); ask sir

            return freePartitionSpace;





            
            // Runtime runtime = Runtime.getRuntime();
            // Process process = runtime.exec("wmic logicaldisk get size,freespace,caption");    // ask sir if hava method required??        
            
            
            // BufferedReader systemInformationReader = new BufferedReader( new InputStreamReader( process.getInputStream() ) );
               
            // StringBuilder stringBuilder = new StringBuilder();
            
            // String line;
    
            // while ((line = systemInformationReader.readLine()) != null)
            // {
            //     stringBuilder.append(line);
            //     stringBuilder.append(System.lineSeparator());
                
            // }
            
            // String a;
            // a=stringBuilder.toString().trim();
            // return a;            
        }

        double getjvm_tram()
        {
            Runtime instance = Runtime.getRuntime();
            double vmtram=instance.totalMemory() / mb;
            return vmtram;
        }

        double getjvm_uram()
        {
            Runtime instance = Runtime.getRuntime();
            double vmuram=instance.totalMemory()-instance.freeMemory() / mb;
            return vmuram;
        }

        double getjvm_fram()
        {
            Runtime instance = Runtime.getRuntime();
            double vmfram=instance.freeMemory() / mb;
            return vmfram;            
        }
          
        
        // add  misc= thread and process,gc,deadlock;

    }

public class run
{   
      public static void main(String args[]) throws IOException
    {
        
        //double d;
        //MProcessor mp=new MProcessor();        

        WindowsSystemInformation wsi= new WindowsSystemInformation();      

        String cpu;
        double tram,aram;
        double hdd,jtram,juram,jfram;

        //tram=wsi.gettram(); // find a way to extract this number and make it GB and return meanwhile string is op
        //aram=wsi.getaram();  // also    tram-aram=uram  

        cpu=wsi.get_cpu();
        tram=wsi.get_tram();
        aram=wsi.get_aram();
        jtram=wsi.getjvm_tram();
        juram=wsi.getjvm_uram();
        jfram=wsi.getjvm_fram();
        hdd=wsi.get_hdd();

        System.out.println();
        System.out.println("CPU " + cpu + "%");
        System.out.println();  

        System.out.println("Total Physical Memory : " +tram + " GB ");
        System.out.println();

        System.out.println("Available Free Physical Memory : " +aram + " GB ");
        System.out.println();

        System.out.println("Free Space in drive C: : " + hdd + " GB");
        System.out.println();  

        System.out.println("JVM Total Memory: " + jtram + " Mb"); 
        System.out.println();	

		System.out.println("JVM Free Memory: " + jfram +" Mb"); 
        System.out.println();
        
        System.out.println("JVM Used Memory: " + juram +" Mb");   
        System.out.println();  

                    
    }    
}

