package com.example.android.movies.util

import org.junit.Test
import org.junit.Assert.*

class TextUtilTests {

    @Test
    fun year_isCorrect(){
        assertEquals("2004",TextUtil.getYearFromDate("2004-03-19"))
    }

    @Test
    fun moneyConversionToText(){
        assertEquals("Revenue: $500",TextUtil.convertMoney("Revenue:",500))
        assertEquals("Revenue: $2k",TextUtil.convertMoney("Revenue:",2130))
        assertEquals("Revenue: $244k",TextUtil.convertMoney("Revenue:",243530))
        assertEquals("Revenue: $3m",TextUtil.convertMoney("Revenue:",3400000))
        assertEquals("Revenue: $35m",TextUtil.convertMoney("Revenue:",34600000))
        assertEquals("Revenue: $2bn",TextUtil.convertMoney("Revenue:",1844243910))
        assertEquals("Revenue: $183bn",TextUtil.convertMoney("Revenue:",183144243910))
    }

    @Test
    fun convertToStringList(){
        class TestClass(val i:Int,val name:String)
        val list = ArrayList<TestClass>(2)
        list.add(TestClass(1,"Name 1"))
        list.add(TestClass(2,"Name 2"))
        list.add(TestClass(3,""))
        assertEquals("Start: Name 1, Name 2",
                TextUtil.convertToCommaSeparatedString("Start:",list,{it->it.name}))
    }

}