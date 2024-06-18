import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitViewController
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIViewController

actual fun getPlatformName(): String = "iOS"

fun MainViewController(
    createScanbotBarcodeView: ((String) -> Unit) -> UIViewController,
) = ComposeUIViewController {
    App(
        createBarcodeScannerView = { onBarcodeScannedCallback ->
            ScanbotBarcodeView {
                createScanbotBarcodeView { barcode ->
                    onBarcodeScannedCallback.onBarcodeScanned(barcode)
                }
            }
        }
    )
}

@OptIn(ExperimentalForeignApi::class)
@Composable
fun ScanbotBarcodeView(createScanbotBarcodeView: () -> UIViewController) {
    UIKitViewController(
        factory = {
            createScanbotBarcodeView()
        },
        modifier = Modifier.fillMaxSize(),
    )
}