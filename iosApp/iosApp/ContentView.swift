import UIKit
import SwiftUI
import shared
import ScanbotBarcodeScannerSDK

struct ComposeView: UIViewControllerRepresentable {
    init() {
    }
    
    func makeUIViewController(context: Context) -> UIViewController {
        return Main_iosKt.MainViewController(createScanbotBarcodeView: { (callback: @escaping (String) -> KotlinUnit) -> UIViewController in
            let configuration = SBSDKUI2BarcodeScannerConfiguration()
            let viewController = SBSDKUI2BarcodeScannerViewController.create(with: configuration) { controller, isCancelled, error, result in
                let items = result?.items
                let firstBarcode = items?.first
                let firstText = firstBarcode?.text
                callback(firstText!)
            }
            return viewController
        })
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

struct ContentView: View {
    var body: some View {
            ComposeView().ignoresSafeArea(.keyboard)
    }
}
