import java.io.*;
import java.util.Objects;

public class Implementations {

    public static void copyFileByteByByte(File from, File to) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try{
            inputStream = new FileInputStream(from);
            outputStream = new FileOutputStream(to);

            int end = -1;

            while((end = inputStream.read()) != -1) {
                outputStream.write(end);
            }
        }
        catch (IOException e) {
            System.out.println("Exception in method: copyFileByteByByte");
        } finally {
            if(inputStream!=null)
                inputStream.close();
            if(outputStream!=null) {
                outputStream.flush();
                outputStream.close();
            }
        }
    }

    public static void printConentOfTxtFIle(File f, PrintStream printer) throws IOException {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(f));
            String line = null;

            while((line = reader.readLine()) != null) {
                printer.println(line);
            }
        }
        catch (IOException exception) {
            System.out.println("Exception in method: printConentOfTxtFIle");
        } finally {
            if(reader!=null)
                reader.close();
        }
    }

    public static void readContentFromStd(OutputStream to) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try{
            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new BufferedWriter(new OutputStreamWriter(to));

            String line = null;

            while((line = reader.readLine()) != null) {
                if(line.equals("KRAJ"))
                    break;
                writer.write(line);
                writer.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("Exception in method: readContentFromStd");
        } finally {
            if(reader!=null)
                reader.close();
            if(writer!=null) {
                writer.flush();
                writer.close();
            }
        }
    }

    public static void memoryUnsafeTxtFileCopy(File from, File to) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try{
            reader = new BufferedReader(new FileReader(from));
            writer = new BufferedWriter(new FileWriter(to));

            StringBuilder builder = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            writer.write(builder.toString());
        }
        catch (IOException e) {
            System.out.println("Exception in method: memoryUnsafeTxtFileCopy");
        }
        finally {
            if(reader!=null)
                reader.close();
            if(writer!=null) {
                writer.flush();
                writer.close();
            }
        }
    }

    public static void memorySafeTxtFileCopy(File from, File to) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try{
            reader = new BufferedReader(new FileReader(from));
            writer = new BufferedWriter(new FileWriter(to));

            String line = null;
            while((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("Exception in method: memorySafeTxtFileCopy");
        }
        finally {
            if(reader!=null)
                reader.close();
            if(writer!=null) {
                writer.flush();
                writer.close();
            }
        }
    }

    public static void readFileWithLineNumber(File from, OutputStream os) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        PrintWriter print = null;

        try{
            reader = new BufferedReader(new FileReader(from));
            writer = new BufferedWriter(new OutputStreamWriter(os));
            print = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));
            String line = null;
            int lineCounter  = 0;

            while((line = reader.readLine()) != null) {
                writer.write(lineCounter++ + ". " + line);
            }
        }
        catch (IOException e) {
            System.out.println("Exception in method: readFileWithLineNumber");
        }
        finally {
            if(reader!=null)
                reader.close();
        }
    }

    public static void writeToTxtFile(File to, String text, Boolean append) throws IOException {
        BufferedWriter writer = null;

        try{
            writer = new BufferedWriter(new FileWriter(to, append));
            writer.write(text);
        }
        catch (IOException e) {
            System.out.println("Exception in method: writeToTxtFile");
        } finally {
            if(writer!=null) {
                writer.flush();
                writer.close();
            }
        }
    }

    public static void writeBinaryDataToBfFile(File to) throws IOException {

        DataOutputStream out = null;
        try{
            out = new DataOutputStream(new FileOutputStream(to));
            out.writeUTF("Ucime OS");
            out.writeInt(12 );
            out.writeDouble(12.12);
        }
        catch (IOException e) {
            System.out.println("Exception in method: writeBinaryDataToBfFile");
        } finally {
            if(out!=null) {
                out.flush();
                out.close();
            }
        }
    }

    public static void readBinaryDataToOutputStream(File from) throws IOException {
        DataInputStream in = null;

        try{
            in = new DataInputStream(new FileInputStream(from));
            System.out.println(in.readUTF());
            System.out.println(in.readInt());
            System.out.println(in.readDouble());
        }
        catch (IOException e) {
            System.out.println("Exception in method: readBinaryDataToOutputStream");
        } finally {
            if(in!=null) {
                in.close();
            }
        }
    }

    public static void writeToRandomAccessFile (File to) throws IOException {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(to, "rw");
            for(int i=0; i<20; i++) {
                raf.writeDouble(i*1.4);
            }
            raf.writeUTF("KRAJ");
        } catch (IOException e) {
            System.out.println("Exception in method: readBinaryDataToOutputStream");
        } finally {
            if(raf!=null)
            {
                raf.close();
            }
        }
    }


    public static void main(String[] args) throws IOException {
        copyFileByteByByte(new File("text1.txt"), new File("text2.txt"));
        printConentOfTxtFIle(new File("text1.txt"), System.out);
//        OutputStream out = new FileOutputStream("zapisi.txt");
//        readContentFromStd(out);

        String text = "Zdravo kolegi vezbame OS\n zdravoooo";
        writeToTxtFile(new File("zapisiString.txt"), text, false);
        String text1 = "Zdravo kolegi OS\n okokok";
        writeToTxtFile(new File("zapisiString.txt"), text1, true);
        memoryUnsafeTxtFileCopy(new File("MemoryUnsafe1.txt"), new File("MemoryUnsafe2.txt"));
        //readFileWithLineNumber(new File("readingLine.txt"), System.out);
        writeBinaryDataToBfFile(new File("writeDATA.txt"));
        readBinaryDataToOutputStream(new File("writeDATA.txt"));
        writeToRandomAccessFile(new File("raf.txt"));
    }
}
