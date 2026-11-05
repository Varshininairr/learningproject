package com.example.learningproject
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon

import android.R
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningproject.ui.theme.LearningProjectTheme

data class Contact(val name: String, val phone: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var typedNames =  remember {
                mutableStateOf("")
            }
            var typedPhoneNumber =  remember {
                mutableStateOf("")
            }
            var contacts by remember { mutableStateOf(mapOf<Int, Contact>()) }
            var nextId by remember { mutableStateOf(0) }
            val addContact = {
                if (typedNames.value.isNotBlank()) {
                    contacts = contacts + (nextId to Contact(typedNames.value, typedPhoneNumber.value))
                    nextId++
                    typedNames.value = ""
                    typedPhoneNumber.value = ""
                }
            }
            var showDialog by remember { mutableStateOf(false) }
            LearningProjectTheme {
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text("Add contact") },
                        text = {
                            Column {
                                OutlinedTextField(
                                    value = typedNames.value,
                                    onValueChange = { typedNames.value = it },
                                    label = { Text("Name") },
                                    singleLine = true
                                )
                                OutlinedTextField(
                                    value = typedPhoneNumber.value,
                                    onValueChange = { typedPhoneNumber.value = it },
                                    label = { Text("Phone Number") },
                                    singleLine = true
                                )
                            }
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                addContact()
                                showDialog = false
                            }) { Text("Add") }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDialog = false }) { Text("Cancel") }
                        }
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    verticalArrangement = Arrangement.Top
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = "Contacts", fontSize = 30.sp)

                        Box(modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { showDialog = true }
                            .background(
                                color = androidx.compose.ui.graphics.Color.LightGray,
                                shape = RoundedCornerShape(10.dp)
                            )
                            , contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add item",
                                modifier = Modifier
                                    .size(35.dp)
                            )
                        }
                    }
                    LazyColumn() {
                        items(contacts.entries.toList(), key = { it.key }) { (_, contact) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 10.dp).border(
                                        width = 1.dp,
                                        color = androidx.compose.ui.graphics.Color.LightGray,
                                        shape = RoundedCornerShape(10.dp)
                                    ),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(modifier = Modifier.width(10.dp)){}
                                Box(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .background(
                                            color = androidx.compose.ui.graphics.Color.Blue,
                                            shape = CircleShape
                                        )
                                ){
                                    Text(
                                        text = contact.name.first().toString(),
                                        modifier = Modifier.align(Alignment.Center),
                                        color = androidx.compose.ui.graphics.Color.White
                                    )
                                }
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start,
                                ) {
                                    Text(
                                        text = contact.name,
                                        modifier = Modifier.padding(10.dp),
                                        fontSize = 30.sp,
                                        color = androidx.compose.ui.graphics.Color.Black
                                    )
                                    Text(
                                        text = contact.phone,
                                        modifier = Modifier.padding(10.dp),
                                        fontSize = 20.sp,
                                        color = androidx.compose.ui.graphics.Color.Gray
                                    )
                                }
                            }
                        }
                    }
            }}
        }
    }
}
