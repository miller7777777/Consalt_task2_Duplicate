package com.consalt.duplicates;

/*
 Duplicates
Составить программу для обработки файла по следующему алгоритму. Задается входной файл, содержащий текстовые строки. Программа обрабатывает его и
создает в указанном месте выходной файл, содержащий отсортированные по алфавиту неповторяющиеся строки исходного файла. В конце каждой строки в
квадратных скобках указывается количество повторений данной строки во входном файле. В качестве входных параметра в метод передаются два файла: первый – входной, второй – выходной. Метод возвращает true тогда и только тогда, когда обработка файла прошла успешно. В случае возникновения ошибок программа
должна вернуть false. Не гарантируется, что данные файлы существуют. В случае, если выходной файл не существует, он должен быть создан. Если он существует, необходимо дописать результат выполнения программы, без перезаписи уже содержащейся там информации.
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {


        //считываем имена входного и выходного файлов
        String inputFileName = args[0];
        String outputFileName = args[1];
        boolean result;

        //получаем результат работы метода - true или false
        result = work(inputFileName, outputFileName);
        System.out.println(result);


    }

    private static boolean work(String inputFileName, String outputFileName) {
        FileReader fileReader;
        FileWriter fileWriter;
        BufferedReader reader;
        File inputFile;
        File outputFile;
        TreeMap<String, Integer> map;
        boolean res;

        inputFile = new File(inputFileName);
        outputFile = new File(outputFileName);

        map = new TreeMap<String, Integer>();

        try {
            //пытаемся читать строки из входного файла
            fileReader = new FileReader(inputFile);
            reader = new BufferedReader(fileReader);

            String s;

            //пока в файле есть строки - добавляем их в коллекцию в качестве ключей. Если ключ уже есть в коллекции, увеличиваем его значение на 1
            while ((s = reader.readLine()) != null) {

                if (!map.containsKey(s)) {
                    map.put(s, 1);
                } else {
                    map.put(s, map.get(s) + 1);
                }

            }
            reader.close();
            fileReader.close();

            //пытаемся записать строки в выходной файл. Если файл не существует, он будет создан. Если файл существует, строки будут дописаны к его содержимому.
            fileWriter = new FileWriter(outputFile, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (String s1 : map.keySet()) {

                writer.write(s1 + " [" + map.get(s1) + "]" + "\n");
                writer.flush();

            }
            writer.close();
            fileWriter.close();
            res = true;


        } catch (FileNotFoundException e) {
            System.err.println("No such file!");
            res = false;
        } catch (IOException e) {
            res = false;
        }
        return res;
    }

}
