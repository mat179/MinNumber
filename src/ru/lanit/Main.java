package ru.lanit;

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        long m = System.currentTimeMillis();
       // generateFile();
        File f = new File("random.txt");
        minNumberFromFile(f);

        //minNumberRandom();
        System.out.println((System.currentTimeMillis()-m));
    }

    public static void minNumberRandom(){
        Random random = new Random();
        int count = random.nextInt(1000000);
        boolean[] arr = new boolean[1000001];
        random.ints(-1000000000,1000000000)
                .limit(count)
                .filter(x -> (x>0)&&(x<=1000000))
                .forEach(x ->arr[x] = true);

        System.out.println(getMinFromArray(arr));

    }


    public static void  minNumberFromFile(File f){
        try (Scanner sc = new Scanner(new FileReader(f))) {
            List<Integer> list = new LinkedList<>();
            if (!f.exists())
                throw new FileNotFoundException();
            sc.useDelimiter(" ");
            while (sc.hasNext()){
                list.add(sc.nextInt());
            }
            boolean[] arr = new boolean[1000001];
            list.stream()
                 .filter(x -> (x>0)&&(x<=1000000))
                 .forEach(x ->arr[x] = true);

            System.out.println(getMinFromArray(arr));
        } catch (FileNotFoundException e){
            System.out.println("Файл не найден");
        }
    }

    private static int getMinFromArray(boolean[] arr){
        for (int i = 1; i<arr.length; i++) {
            if (!arr[i]){
                return i;
            }
        }
        return 1000001;
    }

    public static void generateFile(){
        File f = new File("random.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))){
            if (f.exists())
                f.delete();
            f.createNewFile();
            StringBuilder builder = new StringBuilder();
            Random random = new Random();
            int count =random.nextInt(1000000);
            random.ints(-1000000000,1000000000)
                    .limit(count)
                    .forEach(x -> builder.append(x).append(" "));
            writer.append(builder);
            writer.flush();

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


}
