import SwiftUI
import common

@main
struct iOSApp: App {
    
    init() {
        Graph.init()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
