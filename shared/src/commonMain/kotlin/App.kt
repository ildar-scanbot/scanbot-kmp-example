import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun App(
    createBarcodeScannerView: @Composable (OnBarcodeScanned) -> Unit
) {
    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            var showContent by remember { mutableStateOf(false) }
            var resultText by remember { mutableStateOf("") }
            Button(onClick = {
                showContent = !showContent
            }) {
                Text("Open Barcode Scanner")
            }
            Text(resultText)
            if (showContent) {
                createBarcodeScannerView(object : OnBarcodeScanned {
                    override fun onBarcodeScanned(barcode: String) {
                        resultText = barcode
                        // TODO: If want to hide view after scanning:
                        // showContent = false
                    }

                    override fun onBarcodeScannerClosed() {
                        showContent = false
                    }
                })
            }
        }
    }
}

interface OnBarcodeScanned {
    fun onBarcodeScanned(barcode: String)
    fun onBarcodeScannerClosed()
}


expect fun getPlatformName(): String