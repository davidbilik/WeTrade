package dev.davidbilik.adc.wetrade

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.davidbilik.adc.wetrade.theme.ui.Green
import dev.davidbilik.adc.wetrade.theme.ui.Red
import dev.davidbilik.adc.wetrade.theme.ui.WeTradeTheme
import dev.davidbilik.adc.wetrade.theme.ui.White

@Composable
fun HomeScreen() {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Navigation()

            BalanceTexts()

            TransactButton()

            Spacer(modifier = Modifier.height(16.dp))

            FilterBar()

            Spacer(modifier = Modifier.height(16.dp))

            Graph()

            Spacer(modifier = Modifier.height(32.dp))

            StockPositions()
        }
    }
}

@Composable
private fun StockPositions() {
    Surface(
        color = MaterialTheme.colors.surface
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Positions",
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .paddingFromBaseline(top = 40.dp, bottom = 24.dp)
            )

            dummyStocks.forEach {
                StockItem(stock = it)
            }
        }
    }
}

@Composable
private fun Graph() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.home_illos),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun FilterBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val buttons = listOf("Week", "ETFs", "Stocks", "Funds", "Crypto")
        buttons.forEachIndexed { index, text ->
            FilterButton(text, isDropdown = index == 0)
        }
    }
}

@Composable
private fun TransactButton() {
    PrimaryButton(
        text = "Transact", modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        onClick = {})
}

@Composable
private fun BalanceTexts() {
    Text(
        "Balance", style = MaterialTheme.typography.subtitle1, modifier = Modifier.paddingFromBaseline(
            top = 32.dp,
            bottom = 8.dp
        )
    )

    Text(
        "$73,589.01", style = MaterialTheme.typography.h1, modifier = Modifier.paddingFromBaseline(
            top = 48.dp,
            bottom = 24.dp
        )
    )

    Text(
        "+412.35 today",
        style = MaterialTheme.typography.subtitle1,
        color = Green, modifier = Modifier.paddingFromBaseline(
            top = 16.dp,
            bottom = 32.dp
        )
    )
}

@Composable
private fun FilterButton(
    text: String,
    isDropdown: Boolean
) {
    OutlinedButton(
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.Transparent,
            backgroundColor = Color.Transparent
        ),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, White),
        onClick = { /*TODO*/ },
        modifier = Modifier.height(40.dp)
    ) {
        Text(
            text, style = MaterialTheme.typography.body1,
            color = White,
        )
        if (isDropdown) {
            Icon(Icons.Default.ExpandMore, contentDescription = null, tint = White, modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
private fun Navigation() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NavigationText("Account", modifier = Modifier.weight(1f), enabled = true)
        NavigationText("Watchlist", modifier = Modifier.weight(1f), enabled = false)
        NavigationText("Profile", modifier = Modifier.weight(1f), enabled = false)
    }
}

@Composable
private fun NavigationText(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean
) {
    Text(
        text = text.toUpperCase(),
        style = MaterialTheme.typography.button,
        textAlign = TextAlign.Center,
        modifier = modifier.paddingFromBaseline(
            top = 64.dp,
            bottom = 8.dp
        ),
        color = if (enabled) White else White.copy(alpha = 0.4f)
    )
}

@Composable
fun StockItem(stock: Stock) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp)
    ) {
        Divider()

        Row(
            modifier = Modifier
                .fillMaxSize(), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.width(80.dp)
            ) {
                Text(text = stock.value, style = MaterialTheme.typography.body1)
                Text(
                    "${if (stock.delta > 0) "+" else ""}%.2f".format(stock.delta),
                    style = MaterialTheme.typography.body1,
                    color = if (stock.delta > 0) Green else Red
                )
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(stock.title, style = MaterialTheme.typography.h3)
                Text(stock.subtitle, style = MaterialTheme.typography.body1)
            }
            Image(painter = painterResource(id = stock.graphRes), contentDescription = null)
        }
    }
}

@Preview
@Composable
fun DarkHomePreview() {
    WeTradeTheme(darkTheme = true) {
        HomeScreen()
    }
}
