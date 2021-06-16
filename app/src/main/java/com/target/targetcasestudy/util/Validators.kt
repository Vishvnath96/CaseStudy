package com.target.targetcasestudy.data

/**
 * For an explanation of how to validate credit card numbers read:
 *
 * https://www.freeformatter.com/credit-card-number-generator-validator.html#fakeNumbers
 *
 * This contains a breakdown of how this algorithm should work as
 * well as a way to generate fake credit card numbers for testing
 *
 * The structure and signature of this is open to modification, however
 * it *must* include a method, field, etc that returns a [Boolean]
 * indicating if the input is valid or not
 *
 * Additional notes:
 *  * This method does not need to validate the credit card issuer
 *  * This method must validate card number length (13 - 19 digits), but does not
 *    need to validate the length based on the issuer.
 *
 * @param creditCardNumber - credit card number of (13, 19) digits
 * @return true if a credit card number is believed to be valid,
 * otherwise false
 */

object Validators {


    @JvmStatic
    fun validateCreditCard(creditCardNumber: String): Boolean {

        return false
    }


    // Return true if the card number is valid
    fun isValid(number: Long): Boolean {
        return (getSize(number) >= 13 &&
                getSize(number) <= 19 &&
                (prefixMatched(number, 4) ||
                        prefixMatched(number, 5) ||
                        prefixMatched(number, 37) ||
                        prefixMatched(number, 6)) &&
                (sumOfDoubleEvenPlace(number) +
                        sumOfOddPlace(number)) % 10 == 0)
    }

    // Get the result from Step 2
    fun sumOfDoubleEvenPlace(number: Long): Int {
        var sum = 0
        val num = number.toString() + ""
        var i = getSize(number) - 2
        while (i >= 0) {
            sum += getDigit((num[i].toString() + "").toInt() * 2)
            i -= 2
        }
        return sum
    }

    // Return this number if it is a single digit, otherwise,
// return the sum of the two digits
    fun getDigit(number: Int): Int {
        return if (number < 9) number else number / 10 + number % 10
    }

    // Return sum of odd-place digits in number
    fun sumOfOddPlace(number: Long): Int {
        var sum = 0
        val num = number.toString() + ""
        var i = getSize(number) - 1
        while (i >= 0) {
            sum += (num[i].toString() + "").toInt()
            i -= 2
        }
        return sum
    }

    // Return true if the digit d is a prefix for number
    fun prefixMatched(number: Long, d: Int): Boolean {
        return getPrefix(number, getSize(d.toLong())) == d.toLong()
    }

    // Return the number of digits in d
    fun getSize(d: Long): Int {
        val num = d.toString() + ""
        return num.length
    }

    // Return the first k number of digits from
// number. If the number of digits in number
// is less than k, return number.
    fun getPrefix(number: Long, k: Int): Long {
        if (getSize(number) > k) {
            val num = number.toString() + ""
            return num.substring(0, k).toLong()
        }
        return number
    }

}