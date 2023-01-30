package com.example.cryptocurrency.presentation.coin_list.components

import androidx.compose.ui.Modifier
import com.example.cryptocurrency.domain.module.Coin
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CoinListItemKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun assignAnEmptyCryptoToLayoutCheckIfPropertiesAreSetProperly(){
        val coin = Coin(null, null, null, null ,null ,null,null,null,null,null,null)

        composeTestRule.setContent {
            CoinListItem(modifier = Modifier, coin = coin , onItemClick= {})


        }
        composeTestRule.onNode(hasText("Error!")).assertExists()

    }

    @Test
    fun assignCoinToLayoutCheckIfPropertiesAreSetProperly(){
        val coin = Coin(null, null, null, null ,"")

        composeTestRule.setContent {
            CoinListItem(modifier = Modifier, coin = coin , onItemClick = {})


        }
        composeTestRule.onNode(hasText("")).assertExists()

    }

}