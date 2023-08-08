//
//  ComposeView.swift
//  contactsIOS
//
//  Created by Animasahun Ibrahim on 08/08/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import common
import SwiftUI

struct ComposeView: UIViewControllerRepresentable {
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
    }
    
    func makeUIViewController(context: Context) -> some UIViewController {
        MainViewControllerKt.MainViewController()
    }
}
