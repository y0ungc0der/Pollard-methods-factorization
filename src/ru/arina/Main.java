package ru.arina;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.*;
import java.math.*;
import java.lang.*;

public class Main
{

    public static void main(String args[])
    {
        inv();
    }

    public static void inv()
    {
     /* BigInteger - это класс, хранящийся в пакете java.math.
    Он представляет целые числа произвольной длины. */
        BigInteger c = BigInteger.ZERO; // a = 0
        BigInteger n = BigInteger.ZERO; // n = 0
        BigInteger m = BigInteger.ZERO; // x = 0
        int k = 0;

        // Считывание с консоли числа n
        System.out.print("Введите число n: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext())
        {
            n = new BigInteger(scanner.next());
            System.out.println("Введенное n = " + n);
        }

        // Считывание с консоли числа c
        System.out.print("Введите значение с: ");
        if (scanner.hasNext())
        {
            c = new BigInteger(scanner.next());
            System.out.println("Введенное с = " + c);
        }

        if (((n.mod(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO) != 0)) &&
                n.compareTo(BigInteger.valueOf(0)) == 1 && c.compareTo(BigInteger.valueOf(2)) != -1 &&
                (n.subtract(BigInteger.valueOf(2))).compareTo(c) != -1)
        {
            // roPollard(c, n);
            roOnePollard(c, n);
        }

        return;
    }

    /* p - метод Полларда*/
    public static void roPollard(BigInteger c, BigInteger n)
    {
        System.out.println("\n" + "p - метод Полларда: ");

        BigInteger i = BigInteger.ONE;
        BigInteger m = BigInteger.ZERO;
        BigInteger a = c;
        BigInteger b = c;
        BigInteger d;

        // Считывание с консоли числа m
        System.out.print("Введите значение m для функции отображения f = x^(2) + m: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext())
        {
            m = new BigInteger(scanner.next());
            System.out.println("Введенное m = " + m);
        }

        while (true)
        {
            a = (a.pow(2).add(m)).mod(n);
            b = (b.pow(2).add(m)).mod(n);
            b = (b.pow(2).add(m)).mod(n);
            d = (a.subtract(b)).gcd(n);

            System.out.println("Шаг." + i + "\ta = " + a + "\tb = " + b + "\tНОД(a – b, n) = " + d);
            i = i.add(BigInteger.ONE);

            if (d.compareTo(n) == 0)
            {
                System.out.println("Делитель не найден. " + "\n");
                return;
            } else if (d.compareTo(BigInteger.ONE) != 0)
            {
                System.out.println("Делитель p = " + d + "\n");
                return;
            }
        }
    }

    /* (p-1) - метод Полларда*/
    public static void roOnePollard(BigInteger c, BigInteger n)
    {
        System.out.println("\n" + "(p-1) - метод Полларда: ");

        BigInteger ct = BigInteger.ONE;
        BigInteger l = BigInteger.ZERO;
        BigInteger a = c;
        BigInteger d;
        BigInteger l1;
        int j = 0;

        d = a.gcd(n);

        if (d.compareTo(BigInteger.valueOf(2)) >= 0)
        {
            System.out.println("Делитель p = " + d + "\n");
            return;
        }

        boolean[] isPrime;

        // (1) primes <= 21123984  -> 1337317
        // (2) primes <= 25602 -> 2819
        //BigInteger[] b = new BigInteger[17275206];
         BigInteger[] b = new BigInteger[20];
        // BigInteger[] b = new BigInteger[2819];

        // isPrime = prime(320000000);
        isPrime = prime(89);
        // isPrime = prime(105998);

        /* Заполнение массива только целыми числами типа BigInteger */
         for (int i = 2; i <89; i++) {
        // for (int i = 2; i < 105998; i++) {
       // for (int i = 2; i < 320000000; i++)
       // {
            if (isPrime[i])
                 if (j < 20) {
                // if (j < 2819) {
                //if (j < 17275206)
               // {
                    b[j] = BigInteger.valueOf(i);
                    j++;
                } else break;
        }

        for (int i = 0; i < j; i++)
        {
            l1 = l;
            l = BigDecimal.valueOf(Math.log(n.doubleValue()) / Math.log(b[i].doubleValue())).toBigInteger();
            if (l1.compareTo(l) != 0)
                System.out.println("Шаг." + ct + "\tl = " + l + "\tl1 = " + l1 + "\t b = " + b[i]);
            a = a.modPow((b[i].pow(l.intValue())), n);
            // System.out.println("Шаг." + ct + "\tl = " + l + "\ta = " + a + "\t b = " + b[i]);
            ct = ct.add(BigInteger.ONE);
        }

        d = (a.subtract(BigInteger.ONE)).gcd(n);

        if (d.compareTo(BigInteger.ONE) == 0 || d.compareTo(n) == 0)
        {
            System.out.println("Делитель не найден: d =  " + d + "\n");
            return;
        } else
        {
            System.out.println("Делитель p = " + d + "\n");
            return;
        }
    }

    /* Определение простых чисел */
    public static boolean[] prime(int n)
    {
        // изначально предполагаем, что все целые числа являются не простыми
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++)
        {
            isPrime[i] = true;
        }

        int i = 0;

        // Решето Эратосфена
        for (int factor = 2; factor * factor < n; factor++)
        {
            if (isPrime[factor])
            {
                for (int j = factor; factor * j < n; j++)
                {
                    {
                        isPrime[factor * j] = false;
                        i++;
                        //  System.out.println("i = " + i);
                    }

                }
            }
        }
        return isPrime;
    }

}
