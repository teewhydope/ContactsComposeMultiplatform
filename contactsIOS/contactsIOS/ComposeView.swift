//
//  ComposeView.swift
//  contactsIOS
//
//  Created by Animasahun Ibrahim on 29/07/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import common
import SwiftUI

struct ComposeView: UIViewControllerRepresentable {
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
    }
    
    func makeUIViewController(context: Context) -> some UIViewController {
        MainViewControllerKt.mainViewController()
    }
}
