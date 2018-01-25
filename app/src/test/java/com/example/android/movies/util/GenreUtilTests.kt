package com.example.android.movies.util

import android.content.Context
import com.example.android.movies.R
import org.junit.Test
import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class GenreUtilTests {

    @Mock
    internal var context: Context? = null

    @Test
    fun getGenresIdList(){
        val selected1 = BooleanArray(19)
        selected1[4] = true
        selected1[5] = true
        selected1[6] = true
        selected1[9] = true
        selected1[18] = true
        assertEquals("80,99,18,36,37",GenreUtil.getGenresIdList(selected1))
        val selected2 = BooleanArray(19)
        selected2[0] = true
        selected2[1] = true
        selected2[2] = true
        selected2[3] = true
        selected2[17] = true
        assertEquals("28,12,16,35,10752",GenreUtil.getGenresIdList(selected2))
        val selected3 = BooleanArray(19)
        selected3[12] = true
        selected3[13] = true
        selected3[14] = true
        selected3[15] = true
        selected3[16] = true
        assertEquals("9648,10749,878,10770,53",GenreUtil.getGenresIdList(selected3))
        val selected4 = BooleanArray(19)
        selected4[7] = true
        selected4[8] = true
        selected4[10] = true
        selected4[11] = true
        assertEquals("10751,14,27,10402",GenreUtil.getGenresIdList(selected4))
    }

    @Test
    fun getGenresTextList(){
        mockMethods()
        val selected1 = BooleanArray(19)
        selected1[0] = true
        selected1[1] = true
        selected1[2] = true
        selected1[3] = true
        selected1[4] = true
        assertEquals("Action, Adventure, Animation, Comedy, Crime",GenreUtil.getGenreList(selected1,context!!))
        val selected2 = BooleanArray(19)
        selected2[5] = true
        selected2[6] = true
        selected2[7] = true
        selected2[8] = true
        selected2[9] = true
        assertEquals("Documentary, Drama, Family, Fantasy, History",GenreUtil.getGenreList(selected2,context!!))
        val selected3 = BooleanArray(19)
        selected3[10] = true
        selected3[11] = true
        selected3[12] = true
        selected3[13] = true
        selected3[14] = true
        assertEquals("Horror, Music, Mystery, Romance, Sci-Fi",GenreUtil.getGenreList(selected3,context!!))
        val selected4 = BooleanArray(19)
        selected4[15] = true
        selected4[16] = true
        selected4[17] = true
        selected4[18] = true
        assertEquals("TV Movie, Thriller, War, Western",GenreUtil.getGenreList(selected4,context!!))
    }

    private fun mockMethods(){
        MockitoAnnotations.initMocks(this)
        `when`(context!!.getString(R.string.genre_action)).thenReturn("Action")
        `when`(context!!.getString(R.string.genre_adventure)).thenReturn("Adventure")
        `when`(context!!.getString(R.string.genre_animation)).thenReturn("Animation")
        `when`(context!!.getString(R.string.genre_comedy)).thenReturn("Comedy")
        `when`(context!!.getString(R.string.genre_crime)).thenReturn("Crime")
        `when`(context!!.getString(R.string.genre_documentary)).thenReturn("Documentary")
        `when`(context!!.getString(R.string.genre_drama)).thenReturn("Drama")
        `when`(context!!.getString(R.string.genre_family)).thenReturn("Family")
        `when`(context!!.getString(R.string.genre_fantasy)).thenReturn("Fantasy")
        `when`(context!!.getString(R.string.genre_history)).thenReturn("History")
        `when`(context!!.getString(R.string.genre_horror)).thenReturn("Horror")
        `when`(context!!.getString(R.string.genre_music)).thenReturn("Music")
        `when`(context!!.getString(R.string.genre_mystery)).thenReturn("Mystery")
        `when`(context!!.getString(R.string.genre_romance)).thenReturn("Romance")
        `when`(context!!.getString(R.string.genre_sci_fi)).thenReturn("Sci-Fi")
        `when`(context!!.getString(R.string.genre_tv_movie)).thenReturn("TV Movie")
        `when`(context!!.getString(R.string.genre_thriller)).thenReturn("Thriller")
        `when`(context!!.getString(R.string.genre_war)).thenReturn("War")
        `when`(context!!.getString(R.string.genre_western)).thenReturn("Western")
    }

}