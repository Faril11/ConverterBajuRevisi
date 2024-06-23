package org.d3if0006.converterbajurevisi.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import org.d3if0006.converterbajurevisi.R
import org.d3if0006.converterbajurevisi.navigation.Screen



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController){
    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.app_name))
            },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary

                ),
                actions = {
                    IconButton(onClick = {navController.navigate(Screen.About.route)}) {
                        Icon(imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(id = R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary)

                    }
                }
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }

}


@Composable
fun ScreenContent(modifier: Modifier) {


    var hasilsize by rememberSaveable {
        mutableStateOf(0)
    }
    var kondisi by rememberSaveable {
        mutableStateOf(false)
    }



    var berattError by rememberSaveable {
        mutableStateOf(false)
    }

    var tinggiiError by rememberSaveable {
        mutableStateOf(false)
    }

    var berat by remember {
        mutableStateOf("")
    }
    var tinggi by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = stringResource(id = R.string.cb_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier.fillMaxWidth())

        OutlinedTextField(value = berat, onValueChange ={berat= it},
            label = { Text(text = stringResource(id = R.string.berat_badan))},
            isError = berattError,
            trailingIcon = {IconPicker(berattError,"kg")},
            supportingText = { ErrorHint(isError = berattError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),

            modifier=Modifier.fillMaxWidth()
        )
        OutlinedTextField(value = tinggi,
            onValueChange = {tinggi = it},
            label = { Text(text = stringResource(id = R.string.tinggi_badan))},
            isError = tinggiiError,
            trailingIcon = { IconPicker(isError = tinggiiError, unit = "cm")},
            supportingText = { ErrorHint(isError = tinggiiError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = modifier.fillMaxWidth()


        )


        Button(
            onClick =
            {berattError=(berat==""|| berat=="0")
                tinggiiError=(tinggi==""|| tinggi=="0")
                if (berattError||tinggiiError)return@Button

                kondisi = true
                hasilsize = hitungsize(berat.toFloat(),tinggi.toFloat())
            },
            modifier=Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 40.dp, vertical = 20.dp)
        ) {
            Text(text = stringResource(id = R.string.hitung))

        }
        if (kondisi == true){
            Divider(
                modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(text = stringResource(hasilsize),
                style = MaterialTheme.typography.titleLarge)
            Button(onClick = {
                shareData(
                    context = context,
                    message = context.getString(R.string.bagikan_template,
                        berat, tinggi)
                )

            }){
                Text(text = stringResource(R.string.bagikan))
            }
        }
    }


//    fun getSizeRecommendation(berat: Double, tinggi: Double): String {
//        val bmi = berat / ((tinggi / 100) * (berat / 100))
//        val berat =70.0//dalam kilogram
//        val tinggi = 175.0//dalam sentimeter
//        val recommendedSize = getSizeRecommendation(berat, tinggi)
//        println("Ukuran baju yang direkomendasikan: recommendedSize")
//
//        return when {
//            bmi < 18.5 -> "XS"
//            bmi < 22 -> "S"
//            bmi < 25 -> "M"
//            bmi < 30 -> "L"
//            bmi < 35 -> "XL"
//            bmi < 40 -> "XXL"
//            else -> "XXXL"
//        }
//    }



}


@Composable
fun GenderOption(label: String, isSelected: Boolean,modifier: Modifier) {
    Row (
        modifier=modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(selected = isSelected, onClick = null)
        Text(text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier=modifier.padding(start = 8.dp)
        )
    }

}
private fun shareData(context: Context, message: String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT,message)
    }
    if (shareIntent.resolveActivity(context.packageManager)!= null){
        context.startActivity(shareIntent)
    }
}
private fun hitungsize(beratt: Float, tinggii: Float): Int {
    var sizebaju: Int = 0

    if (beratt < 66) {
        if (tinggii <= 165) {
            sizebaju = R.string.size_s
        } else if (tinggii >= 165 && tinggii <= 175) {
            sizebaju = R.string.size_m
        } else if (tinggii >= 176 && tinggii <= 185) {
            sizebaju = R.string.size_l
        } else {
            sizebaju = R.string.size_xl
        }
    } else if (beratt >= 66 && beratt <= 75) {
        if (tinggii <= 165) {
            sizebaju = R.string.size_m
        } else if (tinggii >= 165 && tinggii <= 175) {
            sizebaju = R.string.size_l
        } else if (tinggii >= 176 && tinggii <= 185) {
            sizebaju = R.string.size_l
        } else {
            sizebaju = R.string.size_xl
        }
    } else if (beratt >= 76 && beratt <= 85) {
        if (tinggii <= 165) {
            sizebaju = R.string.size_l
        } else if (tinggii >= 166 && tinggii <= 175) {
            sizebaju = R.string.size_xl
        } else if (tinggii >= 176 && tinggii <= 185) {
            sizebaju = R.string.size_xl
        } else {
            sizebaju = R.string.size_xl
        }
    } else if (beratt >= 86 && beratt <= 92) {
        if (tinggii <= 165) {
            sizebaju = R.string.size_xl
        } else if (tinggii >= 166 && tinggii <= 175) {
            sizebaju = R.string.size_xl
        } else if (tinggii >= 176 && tinggii <= 185) {
            sizebaju = R.string.size_xl
        } else {
            sizebaju = R.string.size_xl
        }
    } else if (beratt >= 93 && beratt <= 100) {
        if (tinggii <= 165) {
            sizebaju = R.string.size_xxl
        } else if (tinggii >= 166 && tinggii <= 175) {
            sizebaju = R.string.size_xxl
        } else if (tinggii >= 176 && tinggii <= 185) {
            sizebaju = R.string.size_xxl
        } else {
            sizebaju = R.string.size_xxl
        }
    }

    return sizebaju
}

@Composable
fun IconPicker(isError: Boolean,unit:String) {
    if (isError){
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else{
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError){
        Text(text = stringResource(id = R.string.input_invalid))
    }

}