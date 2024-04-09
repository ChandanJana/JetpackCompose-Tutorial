package com.zebra.multiselectlistapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zebra.multiselectlistapplication.ui.theme.MultiSelectListApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiSelectListApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var items by remember {
        mutableStateOf((1..20).map {
            ListItem(
                title = "Item $it", isSelected = false
            )
        })
    }
    //items.filter { it.isSelected }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items.size) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    items = items.mapIndexed { index, listItem ->
                        if (index == it){
                            listItem.copy(isSelected = !listItem.isSelected)
                        }else
                            listItem
                    }
                }
                .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = items.get(it).title)
                if (items.get(it).isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "check",
                        tint = Color.Green,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MultiSelectListApplicationTheme {
        Greeting("Android")
    }
}