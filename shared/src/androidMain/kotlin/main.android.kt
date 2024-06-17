import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import io.scanbot.sdk.barcode_scanner.ScanbotBarcodeScannerSDKInitializer
import io.scanbot.sdk.ui_v2.barcode.BarcodeScannerView
import io.scanbot.sdk.ui_v2.barcode.configuration.BarcodeNativeConfiguration
import io.scanbot.sdk.ui_v2.barcode.configuration.BarcodeScannerConfiguration
import io.scanbot.sdk.ui_v2.barcode.configuration.LocalScanbotNativeConfiguration

actual fun getPlatformName(): String = "Android"

@Composable
fun ScanbotBarcodeView(onBarcodeScannedCallback: OnBarcodeScanned) {
    val configuration = BarcodeScannerConfiguration()
    CompositionLocalProvider(
        LocalScanbotNativeConfiguration provides BarcodeNativeConfiguration(
            enableContinuousScanning = true
        )
    ) {
        BarcodeScannerView(
            configuration = configuration,
            onBarcodeScanned = {
                onBarcodeScannedCallback.onBarcodeScanned(it.items.first().text)
            },
            onBarcodeScannerClosed = {
            }
        )
    }
}

fun initialize(application: Application) {
    ScanbotBarcodeScannerSDKInitializer()
        // Replace "YOUR_SCANBOT_SDK_LICENSE_KEY" with your Scanbot SDK license key:
        // .license(application, "YOUR_SCANBOT_SDK_LICENSE_KEY")
        .initialize(application)
}

@Composable
fun MainView() {
    App(
        createBarcodeScannerView = { onBarcodeScanned ->
            ScanbotBarcodeView(onBarcodeScannedCallback = onBarcodeScanned)
        }
    )
}
