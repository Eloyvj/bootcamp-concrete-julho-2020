package br.concrete.bootcamp_concrete_julho_2020

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

class PasswordValidatorTest {
    @Test
    fun givenPasswordShorterThanEight_whenValidate_shouldReturnFalse(){
        val passwordValidator = PasswordValidator()
        val result = passwordValidator.validate("Eloyv@1")
        assertFalse(result)
    }

    @Test
    fun givenPasswordWithoutUppercase_whenValidate_shouldReturnFalse() {
        val passwordValidator = PasswordValidator()
        val result = passwordValidator.validate("eloyvj@1")
        assertFalse(result)
    }

    @Test
    fun givenPasswordWithoutLowercase_whenValidate_shouldReturnFalse() {
        val passwordValidator = PasswordValidator()
        val result = passwordValidator.validate("ELO#$#@1")
        assertFalse(result)
    }

    @Test
    fun givenPasswordWithoutNumber_whenValidate_shouldReturnFalse() {
        val passwordValidator = PasswordValidator()
        val result = passwordValidator.validate("Eloyvj@$")
        assertFalse(result)
    }

    @Test
    fun givenPasswordWithouSpecialChar_whenValidate_shouldReturnFalse() {
        val passwordValidator = PasswordValidator()
        val result = passwordValidator.validate("EloyvjP1")
        assertFalse(result)
    }

    @Test
    fun givenPasswordCorrect_whenValidate_shouldReturnTrue() {
        val passwordValidator = PasswordValidator()
        val result = passwordValidator.validate("Eloyvj@1")
        assertTrue(result)
    }

}