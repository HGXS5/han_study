package cn.han.string;

import org.hamcrest.core.Is;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @Author han_s
 * @Date 2022/8/4 10:35
 * @ProName maven_test
 */
public class StringTestDemo {
    public static void main(String[] args) {
        String dat = "1,2,";
        int indexOf = dat.lastIndexOf(",");
        dat = dat.substring(0, indexOf);
        System.out.println(dat);
        String patg = "youkuspXNDcwMDA3MjI3Mg==,youkuspXNDcwMDA3NTA5Ng==,youkuspXNDcwMDA4NTU5Ng==,youkuspXNDcwMDA5MjI0OA==,youkuspXNDcwMDE2Njg1Mg==,youkuspXNDcwMDEyMjgwMA==,youkuspXNDcwNjkwNjUzMg==,youkuspXNDcwNjkyMzE5Mg==,youkuspXNDcwNjkyNzM4OA==,youkuspXNDcwNTA5ODE0NA==,youkuspXNDcwNTEyOTkxMg==,youkuspXNDcwNTM3MjU1Ng==,youkuspXNDcwNTM3NjE2NA==,youkuspXNDcwNTMxNjQwOA==,youkuspXNDcwNzY1Njc2NA==,youkuspXNDcwNzYyNjUyMA==,youkuspXNDcwODA0OTkyOA==,youkuspXNDcwODI0MTI4OA==,youkuspXNDcwOTE2MzEwOA==,youkuspXNDcxMjg5Nzc0NA==,youkuspXNDQ1ODcxNDI2OA==,youkuspXNDY0MDc4NDYyMA==,youkuspXNDY0MDc4NDYyNA==,youkuspXNDY0MDc4NDYyOA==,youkuspXNDY0MjgzNzQ4OA==,youkuspXNDY0MjI4MjA1Ng==,youkuspXNDY0MjI4MjA2MA==,youkuspXNDY0MjQxMDgzMg==,youkuspXNDY0MTc0NTY2OA==,youkuspXNDY0MTc0NTY3Mg==,youkuspXNDY0MTc0NTY3Ng==,youkuspXNDY0MTc0NzQ1Mg==,youkuspXNDY0MTc0NzQ1Ng==,youkuspXNDY0MTc0NzQ2NA==,youkuspXNDY0MTc0NzQ2OA==,youkuspXNDY0MTc0NzQ3Mg==,youkuspXNDY0MTc0NzQ3Ng==,youkuspXNDY0MTc0NzQ4MA==,youkuspXNDY0MTc0NzQ4NA==,youkuspXNDY0MTc0NzQ4OA==,youkuspXNDY0MTMyNTQ1Mg==,youkuspXNDY0Mzc1NDYzNg==,youkuspXNDY0MzI3NjIwMA==,youkuspXNDY0MzIzMzIwOA==,youkuspXNDY0MzM3MTg0MA==,youkuspXNDY0MzQxOTExMg==,youkuspXNDY0MzYzMTM1Mg==,youkuspXNDY0NDAyMjAyMA==,youkuspXNDY0NDc5NDA2NA==,youkuspXNDY0NDc5NzM0MA==,youkuspXNDY0NDc5NzMyOA==,youkuspXNDY0NDc5NzMzMg==,youkuspXNDY0NDcyNDAyOA==,youkuspXNDY0NDExMzgxMg==,youkuspXNDY0NDg3MjI3Mg==,youkuspXNDY0NDg3NDEwNA==,youkuspXNDY0NDg3NDQyMA==,youkuspXNDY0NDg3NDU2OA==,youkuspXNDY0NDg3NDU3Ng==,youkuspXNDY0NDgwMTA0OA==,youkuspXNDY0NDgwMTA1Mg==,youkuspXNDY0NDgwMTA1Ng==,youkuspXNDY0NDQ0NTQyNA==,youkuspXNDY0NDUyODcyMA==,youkuspXNDY0NDY2MzIwNA==,youkuspXNDY0NDYwNjYwOA==,youkuspXNDY0Njc4MzQ0MA==,youkuspXNDY0Njc4MzQ0NA==,youkuspXNDY0Njc4MzQ0OA==,youkuspXNDY0Njc4MzQ1Mg==,youkuspXNDY0Njc4MzQ2MA==,youkuspXNDY0Njk3NjQ2MA==,youkuspXNDY0NjkxMzY0NA==,youkuspXNDY0NjkxMzY0OA==,youkuspXNDY0NjkxMzY1Mg==,youkuspXNDY0NjkxMzY1Ng==,youkuspXNDY0NjMwMDUwNA==,youkuspXNDY0NjMwMDUxMg==,youkuspXNDY0NjMwMDUxNg==,youkuspXNDY0NjMwMDUyMA==,youkuspXNDY0NjMwMDUyNA==,youkuspXNDY0NjMwMDUyOA==,youkuspXNDY0NjMwNDE5Ng==,youkuspXNDY0NjMwNDIwMA==,youkuspXNDY0NTc3OTkyMA==,youkuspXNDY0NTk4NzMwOA==,youkuspXNDY0NTk4NzMxMg==,youkuspXNDY0NTk4NzMxNg==,youkuspXNDY0NTk4NzMyMA==,youkuspXNDY0NTk4NzMyNA==,youkuspXNDY0NTk4NzMyOA==,youkuspXNDY0NTk5MTIyMA==,youkuspXNDY0NTk5MTIyNA==,youkuspXNDY0NTk5MTIyOA==,youkuspXNDY0NTk5MzYyOA==,youkuspXNDY0NTk5MzYzMg==,youkuspXNDY0NTk5MzYzNg==,youkuspXNDY0NTk5NTQ3Mg==,youkuspXNDY0NTk5NTQ3Ng==,youkuspXNDY0NTMyMDExNg==,youkuspXNDY0NzIwNjE3Mg==,youkuspXNDY0NzkzNjY1Ng==,youkuspXNDY0NzUxOTQ2NA==,youkuspXNDY0ODY5MjMxNg==,youkuspXNDY0OTA1NDQyNA==,youkuspXNDY0OTE2MDE0MA==,youkuspXNDY0OTE2MDE0NA==,youkuspXNDY0OTE2MDE1Ng==,youkuspXNDY0OTE2MDE2MA==,youkuspXNDY0OTE2MDE2NA==,youkuspXNDY0OTE2MDE3Mg==,youkuspXNDY0OTE2MDE3Ng==,youkuspXNDY0OTE2MDEzMg==,youkuspXNDY0OTE2MDEzNg==,youkuspXNDY0OTE2MjQ1Mg==,youkuspXNDY0OTE2MjQ1Ng==,youkuspXNDY0OTE2MjQ2MA==,youkuspXNDY0OTE2MjQ2NA==,youkuspXNDY0OTE2MjQ2OA==,youkuspXNDY0OTE2MTg0MA==,youkuspXNDY0OTE2MTg0NA==,youkuspXNDY0OTE2MTgyOA==,youkuspXNDY0OTE2MTgzMg==,youkuspXNDY0OTE2MTgzNg==,youkuspXNDY0OTE2Mzc4OA==,youkuspXNDY0OTE2Mzc5Ng==,youkuspXNDY0OTE2MzE5Mg==,youkuspXNDY0OTE2MzE5Ng==,youkuspXNDY0OTE2MzgwMA==,youkuspXNDY0OTE2MzIwMA==,youkuspXNDY0OTE2MzIwNA==,youkuspXNDY0OTE2MzIwOA==,youkuspXNDY0OTE2MzIxMg==,youkuspXNDY0OTE2MzIxNg==,youkuspXNDY0OTE2NDMyOA==,youkuspXNDY0OTE2NDMzMg==,youkuspXNDY0OTE2NDMzNg==,youkuspXNDY0OTE4NzE1Ng==,youkuspXNDY0OTE4NzE2MA==,youkuspXNDY0OTE4NzE2NA==,youkuspXNDY0OTE4NzE3Mg==,youkuspXNDY0OTEyMTkyOA==,youkuspXNDY0OTI1NjQ0MA==,youkuspXNDY0OTI1NjQ0NA==,youkuspXNDY0OTI1NjQ0OA==,youkuspXNDY0OTI1NjQ1Mg==,youkuspXNDY0OTI1NjQzNg==,youkuspXNDY0OTI1Nzk0MA==,youkuspXNDY0OTI1Nzk0OA==,youkuspXNDY0OTI1Nzk1Ng==,youkuspXNDY0OTI1Nzk2MA==,youkuspXNDY0OTI1Nzk2NA==,youkuspXNDY0OTI1Nzk2OA==,youkuspXNDY0OTI1Nzk3Mg==,youkuspXNDY0OTI1Nzk3Ng==,youkuspXNDY0OTI1Nzk4MA==,youkuspXNDY0OTI1Nzk4OA==,youkuspXNDY0OTI1Nzk5Mg==,youkuspXNDY0OTI1Nzk5Ng==,youkuspXNDY0OTI1ODAwMA==,youkuspXNDY0OTI1ODAwNA==,youkuspXNDY0OTI1ODAwOA==,youkuspXNDY0OTI3NTczMg==,youkuspXNDY0OTI3NzUyNA==,youkuspXNDY0OTI5MTI3Mg==,youkuspXNDY0OTIwMjAyNA==,youkuspXNDY0OTk1MjU1Ng==,youkuspXNDY0OTk1NTMwOA==,youkuspXNDY0OTM0ODQ0OA==,youkuspXNDY0OTMwNjA3Mg==,youkuspXNDY0OTQ2Nzc2OA==,youkuspXNDY0OTQ3OTk1Mg==,youkuspXNDY0OTQxMTY3Ng==,youkuspXNDY0OTQxNjgxMg==,youkuspXNDY1MDAwMDQxNg==,youkuspXNDY1MDc4MTkxMg==,youkuspXNDY1MDc4Njg0NA==,youkuspXNDY1MDE2NTQ4NA==,youkuspXNDY1MDE5OTU4OA==,youkuspXNDY1MDg0MTExNg==,youkuspXNDY1MDI0MDYwOA==,youkuspXNDY1MDk3MTM1Ng==,youkuspXNDY1MDMzMDEyNA==,youkuspXNDY1MDQwMjE4OA==,youkuspXNDY1MjA0MzU1Ng==,youkuspXNDY1MjcwNDQyOA==,youkuspXNDY1MjE2NjI3Ng==,youkuspXNDY1MjEwNDQwMA==,youkuspXNDY1MjExOTg3Ng==,youkuspXNDY1MjQ5NTAwNA==,youkuspXNDY1MjUxMDQwMA==,youkuspXNDY1MjY0MDc2MA==,youkuspXNDY1MjYzMTc4MA==,youkuspXNDY1MjYzMzU1Mg==,youkuspXNDY1MjYzNzMzNg==,youkuspXNDY1MTA5MjU0NA==,youkuspXNDY1MTAxMjI2MA==,youkuspXNDY1MTAxNDA3Ng==,youkuspXNDY1MTcyODUyNA==,youkuspXNDY1MTE4NjQ0MA==,youkuspXNDY1MTgyNjQ5Mg==,youkuspXNDY1MTIyMzc2NA==,youkuspXNDY1MTIyNjI4MA==,youkuspXNDY1MTIyNzMwOA==,youkuspXNDY1MTIyNzY4OA==,youkuspXNDY1MTIzMTI1Ng==,youkuspXNDY1MTIzMTI2MA==,youkuspXNDY1MTIzMTI2NA==,youkuspXNDY1MTM1NTA1Ng==,youkuspXNDY1MTQ4NDU1Mg==,youkuspXNDY1MTQ4NzQyMA==,youkuspXNDY1MTQ5NDg4NA==,youkuspXNDY1MTU1Mzg2OA==,youkuspXNDY1MTY1MDczMg==,youkuspXNDY1MTY2NTI1Ng==,youkuspXNDY1MTY2Nzk3Ng==,youkuspXNDY1MTY4MjQ2OA==,youkuspXNDY1MTYyNzQ4NA==,youkuspXNDY1MzEzMjg4OA==,youkuspXNDY1MzgyMDE4NA==,youkuspXNDY1MzgyMDE4OA==,youkuspXNDY1MzgyMDE5Mg==,youkuspXNDY1MzgyMDE5Ng==,youkuspXNDY1MzgyMDIwNA==,youkuspXNDY1MzgyODYzNg==,youkuspXNDY1MzI1MTAxMg==,youkuspXNDY1MzIwNzY0MA==,youkuspXNDY1MzIyMTU1Mg==,youkuspXNDY1MzIyODgwMA==,youkuspXNDY1Mzk0MjQ5Mg==,youkuspXNDY1MzkyNDc5Ng==,youkuspXNDY1MzkzMjc3Ng==,youkuspXNDY1MzkzNDQ5Mg==,youkuspXNDY1MzM4MDI4OA==,youkuspXNDY1MzMwNTc1Ng==,youkuspXNDY1MzQ2NzE0OA==,youkuspXNDY1MzQ3MzI0NA==,youkuspXNDY1NDAxNTIwMA==,youkuspXNDY1NDE0NDE4NA==,youkuspXNDY1NDE4MDA2OA==,youkuspXNDY1NDExNTI2OA==,youkuspXNDY1NDg0MDYyMA==,youkuspXNDY1NDgxMTk3Mg==,youkuspXNDY1NDgxMTY4MA==,youkuspXNDY1NDgzOTI5Mg==,youkuspXNDY1NDI1MTA0OA==,youkuspXNDY1NDk3NjcwNA==,youkuspXNDY1NDk3NTc0NA==,youkuspXNDY1NDk5MzkyNA==,youkuspXNDY1NDQ5MDYwOA==,youkuspXNDY1NDU4NTMwMA==,youkuspXNDY1NDY2MTc3Mg==,youkuspXNDY1NDY3MDgxNg==,youkuspXNDY1NDY4MjA4NA==,youkuspXNDY1NDY4MTYyNA==,youkuspXNDY1NjAwMTA4OA==,youkuspXNDY1NjAxOTM4OA==,youkuspXNDY1NjAyNDU1Mg==,youkuspXNDY1NjAyNjM2OA==,youkuspXNDY1NjAyNTg0MA==,youkuspXNDY1NjAyODQxMg==,youkuspXNDY1NjAzMTA4OA==,youkuspXNDY1NjE4MTU4MA==,youkuspXNDY1NjE4NTk2OA==,youkuspXNDY1NjE4ODk5Mg==,youkuspXNDY1NjI0MTAzMg==,youkuspXNDY1NjIxMDg0MA==,youkuspXNDY1Njk2NDE5Mg==,youkuspXNDY1NjQyMjcwNA==,youkuspXNDY1NjQyMjE2MA==,youkuspXNDY1NjY0OTkwNA==,youkuspXNDY1NjYyOTEyNA==,youkuspXNDY1NjYyOTEyOA==,youkuspXNDY1NjYyOTEzMg==,youkuspXNDY1NTAyMzc4NA==,youkuspXNDY1NTAyMzMzNg==,youkuspXNDY1NTAzMjE4NA==,youkuspXNDY1NTAzMjUzMg==,youkuspXNDY1NTAzMTQ1Ng==,youkuspXNDY1NTc3Njc3Mg==,youkuspXNDY1NTcwNDk3Mg==,youkuspXNDY1NTcxMzg4NA==,youkuspXNDY1NTcxNzI3Mg==,youkuspXNDY1NTcyOTY0MA==,youkuspXNDY1NTczNzg2MA==,youkuspXNDY1NTE1MzE2MA==,youkuspXNDY1NTE1ODM5Mg==,youkuspXNDY1NTE1ODM5Ng==,youkuspXNDY1NTE1ODQwMA==,youkuspXNDY1NTE1ODQwOA==,youkuspXNDY1NTE1ODQxMg==,youkuspXNDY1NTE1ODQxNg==,youkuspXNDY1NTE1ODQyNA==,youkuspXNDY1NTE1ODQyOA==,youkuspXNDY1NTE1ODQzMg==,youkuspXNDY1NTE2MDkxMg==,youkuspXNDY1NTE2MDkyMA==,youkuspXNDY1NTE2MDkyNA==,youkuspXNDY1NTE2MDkzMg==,youkuspXNDY1NTgxNjcxMg==,youkuspXNDY1NTIyMDQ0MA==,youkuspXNDY1NTIyMDQ0NA==,youkuspXNDY1NTkxNDIwNA==,youkuspXNDY1NTkzNzkwNA==,youkuspXNDY1NTM4MjQ0OA==,youkuspXNDY1NTM5MzAwOA==,youkuspXNDY1NTU1NDI4MA==,youkuspXNDY1NTY0NjQ5Ng==,youkuspXNDY1NTY1Mjg2NA==,youkuspXNDY1NTYyOTU3Mg==,youkuspXNDY1NTYzMDkxNg==,youkuspXNDY1NzcyNDQyNA==,youkuspXNDY1NzcyNDQyOA==,youkuspXNDY1NzE0OTY3Mg==,youkuspXNDY1NzEwNjgxMg==,youkuspXNDY1NzExNDA5Ng==,youkuspXNDY1Nzg3NzQ0OA==,youkuspXNDY1Nzg3NzYwMA==,youkuspXNDY1Nzk2NTg2OA==,youkuspXNDY1NzQyMTAwNA==,youkuspXNDY1NzQyMTQ3Ng==,youkuspXNDY1NzYwNDY0MA==,youkuspXNDY1ODAzOTE4MA==,youkuspXNDY1ODcxNzUwMA==,youkuspXNDY1ODg1NTM0NA==,youkuspXNDY1OTAyNDE0MA==,youkuspXNDY1OTAzODY0OA==,youkuspXNDY1OTc5NTY0MA==,youkuspXNDY1OTcwMDI4NA==,youkuspXNDY1OTgwMjI0OA==,youkuspXNDY1OTgwMjIyOA==,youkuspXNDY1OTgwMzc3Ng==,youkuspXNDY1OTgwNjExNg==,youkuspXNDY1OTk0MDg2MA==,youkuspXNDY1OTU1NjQ2MA==,youkuspXNDY1OTY0ODkxMg==,youkuspXNDY1OTY2MzQ1Ng==,youkuspXNDY1OTY2NzA1Mg==,youkuspXNDY1OTY5Njc5Mg==,youkuspXNDY2MDAxNTg3Mg==,youkuspXNDY2MDAxNTU3Mg==,youkuspXNDY2MDE0MDY3Mg==,youkuspXNDY2MDE2NzcwOA==,youkuspXNDY2MDE3MDQzMg==,youkuspXNDY2MDEzNjM0NA==,youkuspXNDY2MDEzODAyNA==,youkuspXNDY2MDEzODQzMg==,youkuspXNDY2MDg2MTAxMg==,youkuspXNDY2MDg2NTAwOA==,youkuspXNDY2MDg2ODQ1Mg==,youkuspXNDY2MDg5MTI1Ng==,youkuspXNDY2MDg5ODEwNA==,youkuspXNDY2MDI2MDYxNg==,youkuspXNDY2MDIyNjYyNA==,youkuspXNDY2MDkyNDE2MA==,youkuspXNDY2MDkyNDM0NA==,youkuspXNDY2MDkyODgzMg==,youkuspXNDY2MDkzMzE2MA==,youkuspXNDY2MDkzMzI0MA==,youkuspXNDY2MDkzOTAyMA==,youkuspXNDY2MDM3Njk0OA==,youkuspXNDY2MDM4NTA0OA==,youkuspXNDY2MDQ0MTQ3Mg==,youkuspXNDY2MDYwNDY2NA==,youkuspXNDY2MDYxMTUyNA==,youkuspXNDY2MjAzMjU2NA==,youkuspXNDY2MjAzNjkyNA==,youkuspXNDY2MjAzNjkyOA==,youkuspXNDY2MjAzNjkzMg==,youkuspXNDY2MjAzNjkzNg==,youkuspXNDY2Mjc2MTUwNA==,youkuspXNDY2MjcxNjEyMA==,youkuspXNDY2MjcyMjQyOA==,youkuspXNDY2MjczNzQxNg==,youkuspXNDY2MjE5MzYwNA==,youkuspXNDY2MjEyNTM1Mg==,youkuspXNDY2Mjg1MTA3Ng==,youkuspXNDY2Mjg2MjkxNg==,youkuspXNDY2Mjg3MTUxMg==,youkuspXNDY2MjI4MTI0NA==,youkuspXNDY2MjI4OTcyMA==,youkuspXNDY2MjI5MzEzNg==,youkuspXNDY2MjIxNDE1Ng==,youkuspXNDY2MjIyODIyNA==,youkuspXNDY2MjMwNTkzMg==,youkuspXNDY2MjMwNTkzNg==,youkuspXNDY2MjMyOTI4MA==,youkuspXNDY2MjYyMjI0NA==,youkuspXNDY2MTA3MTgwMA==,youkuspXNDY2MTA3MTgwNA==,youkuspXNDY2MTA3MTgwOA==,youkuspXNDY2MTA3MTgxMg==,youkuspXNDY2MTA3MTgxNg==,youkuspXNDY2MTc4NDk5Ng==,youkuspXNDY2MTgwNzE0NA==,youkuspXNDY2MTk0OTc2MA==,youkuspXNDY2MTk0OTc2OA==,youkuspXNDY2MTk0OTc3Mg==,youkuspXNDY2MTk0OTc3Ng==,youkuspXNDY2MTk0OTc4MA==,youkuspXNDY2MTk0OTc4NA==,youkuspXNDY2MTk0OTc4OA==,youkuspXNDY2MTkwMzk0MA==,youkuspXNDY2MTM0MjgzMg==,youkuspXNDY2MTM4MDM1Mg==,youkuspXNDY2MTMyNTQ4NA==,youkuspXNDY2MTMzMTA2MA==,youkuspXNDY2MTMzMTIwOA==,youkuspXNDY2MTMzNzE1Ng==,youkuspXNDY2MTMzOTgyMA==,youkuspXNDY2MTQ1NjM3Mg==,youkuspXNDY2MTQ2NzQwOA==,youkuspXNDY2MTQwNTkyNA==,youkuspXNDY2MTUxMjY3Ng==,youkuspXNDY2MTUzNDk0OA==,youkuspXNDY2Mzc1MjgzMg==,youkuspXNDY2Mzc2NDU0NA==,youkuspXNDY2MzcwMDA4NA==,youkuspXNDY2MzcwMTQyOA==,youkuspXNDY2MzcwMzQ2MA==,youkuspXNDY2MzcwOTE1Ng==,youkuspXNDY2MzcxMTcwOA==,youkuspXNDY2MzcxNTAwNA==,youkuspXNDY2MzcyNzgwOA==,youkuspXNDY2MzEzMjg1Mg==,youkuspXNDY2Mzg4ODU3Ng==,youkuspXNDY2MzIwMzAyMA==,youkuspXNDY2MzIwMzAyNA==,youkuspXNDY2MzQxMjE1Mg==,youkuspXNDY2MzQxMzAyOA==,youkuspXNDY2MzU0NjE3Ng==,youkuspXNDY2MzU4NTk3Ng==,youkuspXNDY2MzU5MjA0NA==,youkuspXNDY2MzUzOTMwMA==,youkuspXNDY2MzY1MzQ0MA==,youkuspXNDY2MzY1ODY5Ng==,youkuspXNDY2MzY2MDg0OA==,youkuspXNDY2MzY2NDg4NA==,youkuspXNDY2MzY2NTU1Mg==,youkuspXNDY2MzY2ODY1Mg==,youkuspXNDY2MzY3MjQ4NA==,youkuspXNDY2MzY3NDYwNA==,youkuspXNDY2MzY3NjcxMg==,youkuspXNDY2MzY3ODYyOA==,youkuspXNDY2MzY3OTA3Mg==,youkuspXNDY2MzY4MDg3Mg==,youkuspXNDY2MzY4MDg5Ng==,youkuspXNDY2MzY4NDc3Ng==,youkuspXNDY2MzY4NjczMg==,youkuspXNDY2MzY4OTUwNA==,youkuspXNDY2MzY5MTcwOA==,youkuspXNDY2MzY5MzM4OA==,youkuspXNDY2MzY5NjUwNA==,youkuspXNDY2MzY5ODQyMA==,youkuspXNDY2MzY5ODQyNA==,youkuspXNDY2MzY5OTgwMA==,youkuspXNDY2MzY5OTQ0MA==,youkuspXNDY2MzYxMjM4MA==,youkuspXNDY2MzYxNjQxNg==,youkuspXNDY2NDE0MjA1Ng==,youkuspXNDY2NDE2NjY1Mg==,youkuspXNDY2NDEwMjY5Mg==,youkuspXNDY2NDEwNTY2MA==,youkuspXNDY2NDExNTEwNA==,youkuspXNDY2NDExNzkyNA==,youkuspXNDY2NDEzNTUxMg==,youkuspXNDY2NDI0MjIxNg==,youkuspXNDY2NDI1NDUyNA==,youkuspXNDY2NDk1MzYwMA==,youkuspXNDY2NDk1NDM5Ng==,youkuspXNDY2NDk2MjM0NA==,youkuspXNDY2NDkzOTU5Ng==,youkuspXNDY2NDM5NzAxNg==,youkuspXNDY2NDU0NTU2OA==,youkuspXNDY2NDUzNTI4NA==,youkuspXNDY2NjA4MTg1Mg==,youkuspXNDY2NjA4MTg2MA==,youkuspXNDY2NjI5NzA3Mg==,youkuspXNDY2NjI5NzE4MA==,youkuspXNDY2NjkyMTI4NA==,youkuspXNDY2NjM0MDg2NA==,youkuspXNDY2NjM1NjE2NA==,youkuspXNDY2NjM2ODEyOA==,youkuspXNDY2NjM3NDQxNg==,youkuspXNDY2NjM3OTc4OA==,youkuspXNDY2NjQ3NDIwNA==,youkuspXNDY2NjQxNjk3Mg==,youkuspXNDY2NjQyNjkyNA==,youkuspXNDY2NjQyNjM0OA==,youkuspXNDY2NjU4NTE4MA==,youkuspXNDY2NjUxNjg4MA==,youkuspXNDY2NjUyMDI2MA==,youkuspXNDY2NjY0NDAyOA==,youkuspXNDY2NjYxMzQ1Mg==,youkuspXNDY2NTA1NzcxMg==,youkuspXNDY2NTA1OTU4OA==,youkuspXNDY2NTA2Njg0MA==,youkuspXNDY2NTA2ODgwOA==,youkuspXNDY2NTA3MzAyNA==,youkuspXNDY2NTA4NTExMg==,youkuspXNDY2NTAyNTUwNA==,youkuspXNDY2NTAzMDY1Ng==,youkuspXNDY2NTAzMTUzNg==,youkuspXNDY2NTAzNjgyOA==,youkuspXNDY2NTczMjAxNg==,youkuspXNDY2NTczMTk3Mg==,youkuspXNDY2NTE3ODQ3Ng==,youkuspXNDY2NTI0Nzc2MA==,youkuspXNDY2NTI1NDYyMA==,youkuspXNDY2NTI5MDgzNg==,youkuspXNDY2NTIyODI1Ng==,youkuspXNDY2NTIyODI2MA==,youkuspXNDY2NTM0MjI3Mg==,youkuspXNDY2NTQ2NDQwMA==,youkuspXNDY2NTQ5MzE4MA==,youkuspXNDY2NTU5NzkxMg==,youkuspXNDY2NTUwMDEzMg==,youkuspXNDY2NzAzMzc1Ng==,youkuspXNDY2NzAzNDQwMA==,youkuspXNDY2Nzc0ODI0MA==,youkuspXNDY2NzEzMzc2OA==,youkuspXNDY2Nzk0MjU3Mg==,youkuspXNDY2Nzk1MDc3Mg==,youkuspXNDY2NzMxNzI1Ng==,youkuspXNDY2NzMxNzI2NA==,youkuspXNDY2NzY3MjY4NA==,youkuspXNDY2ODA5MTM3Ng==,youkuspXNDY2ODA5MTM4MA==,youkuspXNDY2ODcyNDQyOA==,youkuspXNDY2ODczNjcwOA==,youkuspXNDY2ODE0NzU2NA==,youkuspXNDY2ODE0ODE0NA==,youkuspXNDY2ODE0ODY1Ng==,youkuspXNDY2ODEyOTAxMg==,youkuspXNDY2ODEyOTYwOA==,youkuspXNDY2ODEzMDc5Ng==,youkuspXNDY2ODEzMDgyOA==,youkuspXNDY2ODEzMDI2NA==,youkuspXNDY2ODg2NjA0MA==,youkuspXNDY2ODg2NjM1Ng==,youkuspXNDY2ODg2NjM2MA==,youkuspXNDY2ODQ3MDc3Ng==,youkuspXNDY2ODQwNTYyMA==,youkuspXNDY2ODU1OTM2NA==,youkuspXNDY2ODY3MzI2MA==,youkuspXNDY2ODY4Nzg0NA==,youkuspXNDY2ODYxMjI5Mg==,youkuspXNDY2ODYxMzk1Mg==,youkuspXNDY2OTA5ODEwOA==,youkuspXNDY2OTAyMDAwMA==,youkuspXNDY2OTc5MDk3Ng==,youkuspXNDY2OTczNTk0NA==,youkuspXNDY2OTczNTk0OA==,youkuspXNDY2OTczNTk1Mg==,youkuspXNDY2OTI2OTU3Mg==,youkuspXNDY2OTIxNzQ2NA==,youkuspXNDY2OTIyOTA5Mg==,youkuspXNDY2OTIyOTI4NA==,youkuspXNDY2OTIzMzE1Mg==,youkuspXNDY2OTkwOTk4MA==,youkuspXNDY2OTM0ODAzMg==,youkuspXNDY2OTM3MzYzNg==,youkuspXNDY2OTUxNDA5Mg==,youkuspXNDY3MDA1NzI3Mg==,youkuspXNDY3MDA2NTI2NA==,youkuspXNDY3MDA3MjE0OA==,youkuspXNDY3MDA3MjQwNA==,youkuspXNDY3MDA3ODA5Ng==,youkuspXNDY3MDcyODY1Ng==,youkuspXNDY3MDExNDk1Mg==,youkuspXNDY3MDI5MzA4NA==,youkuspXNDY3MDM0MjUzMg==,youkuspXNDY3MDMxNDEwNA==,youkuspXNDY3MDQ0NDc2NA==,youkuspXNDY3MDQ0NDYwMA==,youkuspXNDY3MDQ1MjA2OA==,youkuspXNDY3MDU3MTk0OA==,youkuspXNDY3MDY5MjAwOA==,youkuspXNDY3MDY5NDU4NA==,youkuspXNDY3MDY5NjA5Ng==,youkuspXNDY3MDYzMDc4OA==,youkuspXNDY3MjE0MTIzNg==,youkuspXNDY3MjE0Njk4OA==,youkuspXNDY3MjEwMTg2OA==,youkuspXNDY3MjEzNzEyMA==,youkuspXNDY3MjIzNDI4OA==,youkuspXNDY3MjIzNDMxMg==,youkuspXNDY3Mjk2NTY1Ng==,youkuspXNDY3Mjk2NzI5Mg==,youkuspXNDY3Mjk3MTIzMg==,youkuspXNDY3MjkwMjk2OA==,youkuspXNDY3MjkzODYyMA==,youkuspXNDY3MjMxNzM4OA==,youkuspXNDY3MjMxNzM5Mg==,youkuspXNDY3MjQ3NDg0NA==,youkuspXNDY3MTA3OTcwMA==,youkuspXNDY3MTA3OTY4MA==,youkuspXNDY3MTA3OTY4NA==,youkuspXNDY3MTA3OTY4OA==,youkuspXNDY3MTA3OTY5Mg==,youkuspXNDY3MTA3OTY5Ng==,youkuspXNDY3MTEwNTYzMg==,youkuspXNDY3MTI3MTAwOA==,youkuspXNDY3MTk3MjcxNg==,youkuspXNDY3MTk3MTcyNA==,youkuspXNDY3MTk3ODE1Ng==,youkuspXNDY3MTM0NzM4OA==,youkuspXNDY3MTM0NzMwOA==,youkuspXNDY3MTM0ODMzNg==,youkuspXNDY3MTQ0MTIwMA==,youkuspXNDY3MTQzMDEzMg==,youkuspXNDY3MTQzNDU1Mg==,youkuspXNDY3MTUwNzQwNA==,youkuspXNDY3MTUxMTkyNA==,youkuspXNDY3MTUyNzE1Ng==,youkuspXNDY3MzA0NDc4MA==,youkuspXNDY3MzAwMjI3Ng==,youkuspXNDY3MzE3ODE2NA==,youkuspXNDY3MzExNDcyNA==,youkuspXNDY3MzI2NzAyNA==,youkuspXNDY3MzI5NjI1Ng==,youkuspXNDY3MzMwNzA0OA==,youkuspXNDY3MzMxMDkyNA==,youkuspXNDY3MzQ3Njc3Ng==,youkuspXNDY3MzQ3NzY4NA==,youkuspXNDY3MzQzNTk4MA==,youkuspXNDY3MzU2NzYyNA==,youkuspXNDY3MzUwNTcwMA==,youkuspXNDY3NDc1NzQzNg==,youkuspXNDY3NDc2NzA4MA==,youkuspXNDY3NDc2NzEwNA==,youkuspXNDY3NDE0OTQ3Mg==,youkuspXNDY3NDE2OTg2MA==,youkuspXNDY3NDE2OTM4OA==,youkuspXNDY3NDE3MTk3Mg==,youkuspXNDY3NDE5MzkwMA==,youkuspXNDY3NDEyMjQ4MA==,youkuspXNDY3NDgxMjgyMA==,youkuspXNDY3NDgxMzY0NA==,youkuspXNDY3NDgzNjA2NA==,youkuspXNDY3NDI4MDE0MA==,youkuspXNDY3NDI4MDE0NA==,youkuspXNDY3NDM0MjgyOA==,youkuspXNDY3NDM0NDE5Mg==,youkuspXNDY3NDM3ODk0OA==,youkuspXNDY3NDM4MTczMg==,youkuspXNDY3NDMzMTI1Ng==,youkuspXNDY3NDY0NjAyMA==,youkuspXNDY3NDY1MjM1Mg==,youkuspXNDY3NDY1NDQyOA==,youkuspXNDY3NDY2NTY5Ng==,youkuspXNDY3Njg5MjU0OA==,youkuspXNDY3NjIxODc3Mg==,youkuspXNDY3NjIxODc3Ng==,youkuspXNDY3Njk4NzQ2OA==,youkuspXNDY3NjkwMTI4OA==,youkuspXNDY3NjkyMDc5Mg==,youkuspXNDY3NjQ4MTQyNA==,youkuspXNDY3NTAwMDI5Mg==,youkuspXNDY3NTE3MzczNg==,youkuspXNDY3NTEwNTgzNg==,youkuspXNDY3NTk1MjU5Mg==,youkuspXNDY3NTM1NDMwMA==,youkuspXNDY3NTQ1MjcwOA==,youkuspXNDY3NTQ1OTMzMg==,youkuspXNDY3NTQxMjc0OA==,youkuspXNDY3NTQxMjc1Mg==,youkuspXNDY3NzAwMTQwOA==,youkuspXNDY3NzcxNTQwNA==,youkuspXNDY3Nzg5NTA0NA==,youkuspXNDY3Nzg5ODI0MA==,youkuspXNDY3Nzg5ODIzMg==,youkuspXNDY3Nzg5ODIzNg==,youkuspXNDY3NzMzNTM5Mg==,youkuspXNDY3NzQ0NTI1Ng==,youkuspXNDY3NzQ2NjcyMA==,youkuspXNDY3NzQ5MTA1Mg==,youkuspXNDY3NzQ5OTY5Ng==,youkuspXNDY3NzUwOTE4OA==,youkuspXNDY3NzUyNDg1Mg==,youkuspXNDY3ODEwMzAzNg==,youkuspXNDY3ODgwNTA4OA==,youkuspXNDY3ODI0MjE5Ng==,youkuspXNDY3ODI0NzAwOA==,youkuspXNDY3ODk1NDM2MA==,youkuspXNDY3ODkwMjY4MA==,youkuspXNDY3ODU5MzU5Mg==,youkuspXNDY3ODYyOTc0NA==,youkuspXNDY3OTg4Njc5Ng==,youkuspXNDY3OTIwNjQ0NA==,youkuspXNDY3OTIwODU3Ng==,youkuspXNDY3OTIwODUwOA==,youkuspXNDY3OTM5NTA3Ng==,youkuspXNDY3OTQ0MDI4MA==,youkuspXNDY3OTU4MDMyOA==,youkuspXNDY4MDA3MTM1Mg==,youkuspXNDY4MDA3NTMxMg==,youkuspXNDY4MDA5NjY4MA==,youkuspXNDY4MDAxNjQwOA==,youkuspXNDY4MDAxNjQxMg==,youkuspXNDY4MDAxNjQxNg==,youkuspXNDY4MDAxNjQyMA==,youkuspXNDY4MDAxNjQzMg==,youkuspXNDY4MDAxNjQzNg==,youkuspXNDY4MDc3MTQzMg==,youkuspXNDY4MDE2MTM0NA==,youkuspXNDY4MDE5MDk1Mg==,youkuspXNDY4MDgyODQ1Mg==,youkuspXNDY4MDk3NTQ0NA==,youkuspXNDY4MDMzMDM3Mg==,youkuspXNDY4MDU1MzM0MA==,youkuspXNDY4MDY2MDE1Ng==,youkuspXNDY4MDY2MTk1Mg==,youkuspXNDY4MDYxNzkwMA==,youkuspXNDY4MjAzOTAwNA==,youkuspXNDY4MjExMTU4MA==,youkuspXNDY4MjExMTU4NA==,youkuspXNDY4MjExMTU4OA==,youkuspXNDY4MjExMTU5Mg==,youkuspXNDY4MjI0OTQ3Ng==,youkuspXNDY4MjI1NTM4MA==,youkuspXNDY4MjI1NzE5Mg==,youkuspXNDY4MjI2MzUwNA==,youkuspXNDY4MjI2NTc2MA==,youkuspXNDY4MjI2NTEzMg==,youkuspXNDY4MjI2Nzg4NA==,youkuspXNDY4MjU5NDc2OA==,youkuspXNDY4MjY0OTUxNg==,youkuspXNDY4MjYwMDk3Ng==,youkuspXNDY4MTA1NTk4OA==,youkuspXNDY4MTc4NjQ3Ng==,youkuspXNDY4MTc4NTI0MA==,youkuspXNDY4MTc4ODYwNA==,youkuspXNDY4MTc5NTgzMg==,youkuspXNDY4MTE4MTA2MA==,youkuspXNDY4MTgwODQ5Mg==,youkuspXNDY4MTgzNzcwOA==,youkuspXNDY4MTIyOTE4OA==,youkuspXNDY4MTM0NzU0MA==,youkuspXNDY4MTM1ODM4NA==,youkuspXNDY4MTMxNzM0NA==,youkuspXNDY4MTMxNzM0OA==,youkuspXNDY4MTY2NjI1Ng==,youkuspXNDY4MzAxMjIyOA==,youkuspXNDY4Mzc4NDYwOA==,youkuspXNDY4Mzg4NTIyMA==,youkuspXNDY4Mzg4NzI4MA==,youkuspXNDY4MzM2MDg0OA==,youkuspXNDY4MzM2MTgzNg==,youkuspXNDY4MzMxMDAyOA==,youkuspXNDY4MzQ3MzI5Ng==,youkuspXNDY4MzQ4ODc1Ng==,youkuspXNDY4MzU5NTc0NA==,youkuspXNDY4MzUwODkyNA==,youkuspXNDY4NDE0MjcwMA==,youkuspXNDY4NDE0MjY5Ng==,youkuspXNDY4NDIwNzYyOA==,youkuspXNDY4NDIwODc3Ng==,youkuspXNDY4NjA3Mjg0MA==,youkuspXNDY4NjA4MTU5Mg==,youkuspXNDY4Njc0NTcxNg==,youkuspXNDY4NjgzNzE1Mg==,youkuspXNDY4NjQxMDc5Ng==,youkuspXNDY4NjY3MDE2OA==,youkuspXNDY4NTA2OTkzMg==,youkuspXNDY4NTA3MTM1Mg==,youkuspXNDY4NTE2MDkwMA==,youkuspXNDY4NTg5ODUyMA==,youkuspXNDY4NTI3MjczNg==,youkuspXNDY4NTk1Nzg1Ng==,youkuspXNDY4NTkwNzE3Mg==,youkuspXNDY4NzIwMDkwMA==,youkuspXNDY4NzkwMDMwOA==,youkuspXNDY4NzQ5NTUwOA==,youkuspXNDY4NzQ5NTUxMg==,youkuspXNDY4NzQ5NTUxNg==,youkuspXNDY4NzY1NzEzNg==,youkuspXNDY4NzY1NzQ5Ng==,youkuspXNDY4ODczNjYwMA==,youkuspXNDY4ODczNjYwNA==,youkuspXNDY4ODczNjYwOA==,youkuspXNDY4ODczNjYxMg==,youkuspXNDY4ODczNjYxNg==,youkuspXNDY4ODczNjYyMA==,youkuspXNDY4ODczNjYyOA==,youkuspXNDY4ODE5MjA4NA==,youkuspXNDY4ODk4MzAzMg==,youkuspXNDY4ODk4NDUyNA==,youkuspXNDY4ODUzNDU2NA==,youkuspXNDY4ODYzMDI2NA==,youkuspXNDY4OTAxNDM1Mg==,youkuspXNDY4OTc1MDQ0NA==,youkuspXNDY4OTkzNTYxNg==,youkuspXNDY4OTM0MjExMg==,youkuspXNDY4OTM0MzA1Mg==,youkuspXNDY5MDAzNzc0OA==,youkuspXNDY5MDEzMjIyNA==,youkuspXNDY5MDEzMjIyOA==,youkuspXNDY5MDI4NTMwMA==,youkuspXNDY5MDM0NjgyNA==,youkuspXNDY5MjcyNjMwMA==,youkuspXNDY5MjE0NjIyNA==,youkuspXNDY5MjE0OTI0OA==,youkuspXNDY5MTA2NzQ4OA==,youkuspXNDY5MTA2ODU0NA==,youkuspXNDY5MTkxNDg0NA==,youkuspXNDY5MTM4MzU0MA==,youkuspXNDY5MTM4MzU0NA==,youkuspXNDY5MTM5NTIwMA==,youkuspXNDY5MTM5OTYwNA==,youkuspXNDY5MTQwMzc0NA==,youkuspXNDY5MzQ4NDc4OA==,youkuspXNDY5MzQ4NjI3Ng==,youkuspXNDY5NDcwMjg1Ng==,youkuspXNDY5NDIzNzkyMA==,youkuspXNDY5NDYwMTQ0NA==,youkuspXNDY5NTQ3MDQyMA==,youkuspXNDY5Nzc2MjcyOA==,youkuspXNDY5Nzc2MjE3Mg==,youkuspXNDY5Nzc2MTA5Mg==,youkuspXNDY5Nzc2Mzc2MA==,youkuspXNDY5Nzc2NDI1Mg==,youkuspXNDY5Nzc2NDYyOA==,youkuspXNDY5Nzc2NjU0NA==,youkuspXNDY5Nzc2NTAyNA==,youkuspXNDY5Nzc2NTUzNg==,youkuspXNDY5NzczOTIwNA==,youkuspXNDY5NzUwMTkxNg==,youkuspXNDY5NzY3NTE4MA==,youkuspXNDY5NzY3NzY2NA==,youkuspXNDY5NzY4MDU5Mg==,youkuspXNDY5ODQ4MTg5Ng==,youkuspXNDY5ODQ4MzE3Mg==,youkuspXNDY5ODU0MTIwMA==,youkuspXNDY5OTk1NjEwMA==";
        String[] strings = patg.split(",");
        System.out.println(strings.length);
//        test3();
//        int a = 2;
//        System.out.println(++a);
//        System.out.println(a++);
//        short s1 = 1;
//        s1 += 1;
////        s1 = s1 + 1;
//        System.out.println(s1);
    }
    public void test1(){
        String time = "1000";
        Integer integer = Integer.valueOf(time);
        int value = integer;
        System.out.println(integer);
    }
    public static void test2(){
        String methodName = "getName";
        int index = methodName.indexOf("get");
        String filedMe = methodName.substring(index+3).toLowerCase();
        System.out.println(filedMe);


    }
    public static void test3(){
        String videoUrl = "https://wasu-hls-test-input.obs.cn-east-2.myhuaweicloud.com/gxyk/89961649321688955";
        String urlNew = "https://itvdev.wasu.cn/wasu-zhjtsyb-ugc-east/";
        String urlOld = "https://wasu-hls-test-input.obs.cn-east-2.myhuaweicloud.com/";
        BiFunction<Long, Long, Long> sum = Long::sum;

        if (videoUrl.contains(urlOld)) {
            StringBuffer sb = new StringBuffer();
            sb.append(urlNew);
            String part = videoUrl.substring(urlOld.length());
            System.out.println(part);
            sb.append(part);
            System.out.println(sb.toString());

        }
    }
}
