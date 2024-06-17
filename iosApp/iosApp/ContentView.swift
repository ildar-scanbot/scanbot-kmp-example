import UIKit
import SwiftUI
import shared
import ScanbotBarcodeScannerSDK

struct ComposeView: UIViewControllerRepresentable {
    @State private var isShowingModal = false
    
    @State var isCancelled: Bool = true
    @State var error: Error?
    @State var scannerResult: SBSDKUI2BarcodeScannerResult?
    
    init() {
    }
    
    func makeUIViewController(context: Context) -> UIViewController {
        return Main_iosKt.MainViewController(createScanbotBarcodeView: { () -> UIViewController in
            let configuration = SBSDKUI2BarcodeScannerConfiguration()
            // configuration.useCase = SBSDKUI2MultipleScanningMode.init()
            let swiftUIView = VStack {
                            
                            SBSDKUI2BarcodeScannerView(configuration: configuration,
                                                       isShown: $isShowingModal,
                                                       error: $error,
                                                       result: $scannerResult)
                            .ignoresSafeArea()
          }
          return UIHostingController(rootView: swiftUIView)
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
