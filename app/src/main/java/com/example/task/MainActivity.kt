package com.example.task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.getSelectedText
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task.Data.AuthRequest
import com.example.task.Data.User
import com.example.task.ui.theme.TaskTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.compose.runtime.getValue
import kotlinx.coroutines.CoroutineExceptionHandler

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TaskTheme {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center,
           modifier = Modifier.fillMaxSize()
                ) {

    var text by remember { mutableStateOf("") }
    var text1 by remember { mutableStateOf("") }
    var resultat = remember { mutableStateOf("") }
        var login = remember { mutableStateOf("") }
        var password = remember { mutableStateOf("") }
        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            throwable.printStackTrace()
        }
    val kc = LocalSoftwareKeyboardController.current

    val callback = {
      login.value=text
        password.value=text1
        TransformedText(
            text = AnnotatedString(text="ololo"),
            offsetMapping = OffsetMapping.Identity
        )
       // clean enter for catch (ex: NumberFormatException) { ""}
        kc?.hide()
    }//work textfield
        Text(text =resultat.value)
        Text(text =login.value )
        Text(text =password.value )
    OutlinedTextField(
       value = text,
       shape= RoundedCornerShape(24.dp),
       label = { Text(text = "login", color = colorResource(id = R.color.purple_200)) },
       onValueChange = {
       text = it },
       textStyle= TextStyle(
       fontSize = 16.sp,
       color = colorResource(id = R.color.purple_200)),
       keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
       keyboardActions= KeyboardActions(onDone = {callback()
       }),
       singleLine=true,
       colors = TextFieldDefaults.outlinedTextFieldColors(
       focusedBorderColor = colorResource(id = R.color.purple_200),
       unfocusedBorderColor = colorResource(id = R.color.purple_200),
       containerColor = colorResource(id = R.color.white),),
          modifier= Modifier
              .padding(top = 50.dp)
              .height(70.dp)
              .width(320.dp) )//one textfield
    OutlinedTextField(
       value = text1,
       shape= RoundedCornerShape(24.dp),
       label = { Text(text = "password", color = colorResource(id = R.color.purple_200)) },
       onValueChange = {
       text1 = it },
       textStyle= TextStyle(
       fontSize = 16.sp,
       color = colorResource(id = R.color.purple_200)),
       keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
       keyboardActions= KeyboardActions(onDone = {callback() }),
       singleLine=true,
       colors = TextFieldDefaults.outlinedTextFieldColors(
       focusedBorderColor = colorResource(id = R.color.purple_200),
       unfocusedBorderColor = colorResource(id = R.color.purple_200),
       containerColor = colorResource(id = R.color.white),),
       modifier= Modifier
           .padding(top = 10.dp)
           .height(70.dp)
           .width(320.dp) )//two textfield
    Button(onClick ={

      CoroutineScope(Dispatchers.IO+coroutineExceptionHandler).launch {
      val user= productApi.auth(
          AuthRequest(
              username =login.value ,
              password = password.value
          )
      )
         // resultat.value=user.gender
          run {
          resultat.value=user.email
          }//close run
      }//close lounch
    },
        Modifier
            .height(60.dp)
            .width(120.dp)
            .padding(top = 10.dp)
       ,colors = ButtonDefaults.buttonColors(colorResource(id = R.color.white)),
       shape = RoundedCornerShape(15.dp),
       border = BorderStroke(width = 3.dp, color = colorResource(id = R.color.purple_200)),
       elevation = ButtonDefaults.elevatedButtonElevation(10.dp)
       ) {
    Text(
       text = "sign in",
       color = colorResource(id = R.color.purple_200),
       fontSize = 20.sp,
       textAlign = TextAlign.Center)

                 }//button close
               }//top column
            }
        }
    }
}
