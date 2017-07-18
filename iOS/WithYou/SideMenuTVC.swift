//
//  SideMenuVC.swift
//  WithYou
//
//  Created by Mamdouh El Nakeeb on 7/3/17.
//  Copyright © 2017 ma33a. All rights reserved.
//

import UIKit

class SideMenuTVC: UITableViewController {

    
    let homeTVC = HomeTVC()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        self.tableView.separatorStyle = UITableViewCellSeparatorStyle.none
        
        self.tableView.backgroundColor = UIColor(red: 51/255, green: 51/255, blue: 51/255, alpha: 1)
        
    }
    
    override func tableView(_ tableView: UITableView, willDisplay cell: UITableViewCell, forRowAt indexPath: IndexPath) {
        cell.backgroundColor = UIColor.clear
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let cell:UITableViewCell = tableView.cellForRow(at: indexPath)!
        cell.contentView.backgroundColor = UIColor(red: 221/255, green: 136/255, blue: 8/255, alpha: 1)
        
        switch indexPath.row {
        case 0:
            UIApplication.tryURL(urls: [
                "https://ma33a.com/messages"
                ])
            break
            
        case 1:
            UIApplication.tryURL(urls: [
                "https://ma33a.com/blog/wp-admin/profile.php"
                ])
            break
            
        case 2:
            UIApplication.tryURL(urls: [
                "https://ma33a.com/blog/about-us-2"
                ])
            break
            
        case 3:
            UIApplication.tryURL(urls: [
                "http://zinakamoura.com"
                ])
            break
            
        case 4:
            UIApplication.tryURL(urls: [
                "https://ma33a.com/podcast"
                ])
            break
            
        case 5:
            UIApplication.tryURL(urls: [
                "https://ma33a.com/blog"
                ])
            break
            
        case 6:
            UIApplication.tryURL(urls: [
                "https://ma33a.com/library"
                ])
            break
            
        case 7:
            UIApplication.tryURL(urls: [
                "https://ma33a.com/school"
                ])
            break
            
        case 8:
            UIApplication.tryURL(urls: [
                "https://ma33a.com/videos"
                ])
            break

        case 9:
            UIApplication.tryURL(urls: [
                "https://ma33a.com/guide"
                ])
            break
            
        case 10:
            homeTVC.fbIntent()
            break
            
        case 11:
            
            homeTVC.smsIntent()
            
            break
            
        case 12:
            UIApplication.tryURL(urls: [
                "https://ma33a.com/download"
                ])
            break
        default:
            break
        }
    }

}


