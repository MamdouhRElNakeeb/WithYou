//
//  HomeTVC.swift
//  WithYou
//
//  Created by Mamdouh El Nakeeb on 7/3/17.
//  Copyright © 2017 ma33a. All rights reserved.
//

import UIKit
import SwiftyButton
import SDWebImage
import MessageUI

class HomeTVC: UITableViewController {

    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tableView.separatorStyle = UITableViewCellSeparatorStyle.none
        
        view.backgroundColor = UIColor.white
        
        let sideMenuImg = UIImage(named: "menu_icon")
        navigationItem.rightBarButtonItem = UIBarButtonItem(image: sideMenuImg, style: .plain, target: self, action: #selector(SSASideMenu.presentRightMenuViewController))
        
    }
    
    func fbIntent() {
        UIApplication.tryURL(urls: [
            "fb://profile/george.dababneh", // App
            "https://www.facebook.com/george.dababneh" // Website if app fails
            ])
    }
    
    func podcastIntent() {
        UIApplication.tryURL(urls: [
            "https://ma33a.com/podcast"
            ])
    }
    
    func libraryIntent() {
        UIApplication.tryURL(urls: [
            "https://ma33a.com/library"
            ])
    }
    
    func schoolIntent() {
        UIApplication.tryURL(urls: [
            "https://ma33a.com/school"
            ])
    }
    
    func guideIntent() {
        UIApplication.tryURL(urls: [
            "https://ma33a.com/guide"
            ])
    }
    
    func articlesIntent() {
        UIApplication.tryURL(urls: [
            "https://ma33a.com/blog"
            ])
    }
    
    func videosIntent() {
        UIApplication.tryURL(urls: [
            "https://ma33a.com/videos"
            ])
    }
    
    func loginIntent() {
        UIApplication.tryURL(urls: [
            "https://ma33a.com//blog/login"
            ])
    }
    
    func registerIntent() {
        UIApplication.tryURL(urls: [
            "https://ma33a.com//blog/login"
            ])
    }
    
    func callIntent() {
        //guard let number = URL(string: "tel://19409") else { return }
        UIApplication.tryURL(urls: [
            "tel://19409", // call
            ])
        
    }
    
    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return 6
    }
    
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let screenWidth = self.view.frame.size.width
        
        let viewWidth: CGFloat = 79
        let rowHeight: CGFloat = 135
        let frameLeft = CGRect(x: (screenWidth / 4) - (viewWidth / 2), y: 0, width: viewWidth, height: rowHeight)
        let frameRight = CGRect(x: (3 * screenWidth / 4) - (viewWidth / 2), y: 0, width: viewWidth, height: rowHeight)
        
        let btnShadow = UIColor(red: 221/255, green: 136/255, blue: 8/255, alpha: 1)
        let btnColor = UIColor(red: 51/255, green: 51/255, blue: 51/255, alpha: 1)
        
        var cell = UITableViewCell()
        
        switch indexPath.row {
            
        case 0:
 
            cell = tableView.dequeueReusableCell(withIdentifier: "SliderTVcell", for: indexPath) as! SliderTVcell
            
            break
            
        case 1:
            let loginPBtn = PressableButton()
            let registerPBtn = PressableButton()
            
            loginPBtn.colors = .init(button: btnShadow, shadow: btnColor)
            loginPBtn.shadowHeight = 4
            loginPBtn.cornerRadius = 8
            loginPBtn.depth = 0.5
            loginPBtn.frame = CGRect(x: (screenWidth / 2) + 2.5, y: 5, width: (screenWidth / 2) - 5, height: 44)
            loginPBtn.setTitle("دخول", for: .normal)
            loginPBtn.setTitleColor(UIColor.white, for: .normal)
            loginPBtn.titleLabel?.font = UIFont(name: "Hacen Extender X4 Super Fit", size: 17)
            
            loginPBtn.addTarget(self, action: #selector(loginIntent), for: .touchUpInside)
    
            registerPBtn.colors = .init(button: btnShadow, shadow: btnColor)
            registerPBtn.shadowHeight = 4
            registerPBtn.cornerRadius = 8
            registerPBtn.depth = 0.5
            registerPBtn.setTitle("تسجيل", for: .normal)
            registerPBtn.setTitleColor(UIColor.white, for: .normal)
            registerPBtn.titleLabel?.font = UIFont(name: "Hacen Extender X4 Super Fit", size: 17)
            registerPBtn.frame = CGRect(x: 5, y: 5, width: (screenWidth / 2) - 10, height: 44)
            
            cell.addSubview(loginPBtn)
            cell.addSubview(registerPBtn)
            cell.heightAnchor.constraint(equalToConstant: 39)
            
            registerPBtn.addTarget(self, action: #selector(loginIntent), for: .touchUpInside)
            
            break
            
        case 2:
            
            let podcastBtn = CustomPressableButton()
            
            podcastBtn.colors = .init(button: btnShadow, shadow: btnColor)
            podcastBtn.shadowHeight = 4
            podcastBtn.cornerRadius = 8
            podcastBtn.frame = CGRect(x: 5, y: 10, width: (screenWidth / 2) - 7.5, height: rowHeight)
            
            let podcastSV = UIStackView(frame: frameLeft)
            podcastSV.translatesAutoresizingMaskIntoConstraints = false
            podcastSV.axis = .vertical
            
            let podcastImage = UIImage(named: "podcast_icon")
            let podcastIcon = UIImageView(image: UIImage(named: "podcast_icon"))
            podcastIcon.frame = CGRect(x: 0, y: 0, width: 30, height: 30)
            podcastIcon.image = podcastImage?.maskWithColor(color: UIColor.white)
            podcastIcon.contentMode = .scaleAspectFit
            podcastIcon.layer.masksToBounds = true
            podcastIcon.translatesAutoresizingMaskIntoConstraints = false
            
            podcastIcon.heightAnchor.constraint(equalToConstant: 70).isActive = true
            podcastIcon.widthAnchor.constraint(equalToConstant: 100).isActive = true
            
            let podcastTitleArabic = UILabel()
            podcastTitleArabic.text = "برامج صوتية"
            podcastTitleArabic.textColor = UIColor.white
            podcastTitleArabic.textAlignment = .center
            podcastTitleArabic.font = UIFont(name: "HacenExtenderX4SuperFit", size: 20)
            
            let podcastTitleEnglish = UILabel()
            podcastTitleEnglish.text = "Podcasts"
            podcastTitleEnglish.textColor = UIColor.white
            podcastTitleEnglish.textAlignment = .center
            podcastTitleEnglish.font = UIFont(name: "Arial", size: 15)
            
            podcastSV.addArrangedSubview(podcastIcon)
            podcastSV.addArrangedSubview(podcastTitleArabic)
            podcastSV.addArrangedSubview(podcastTitleEnglish)
            
            podcastBtn.contentView.addSubview(podcastSV)
            
            
            podcastSV.leftAnchor.constraint(equalTo: podcastBtn.contentView.leftAnchor, constant: 0).isActive = true
            podcastSV.rightAnchor.constraint(equalTo: podcastBtn.contentView.rightAnchor, constant: 0).isActive = true
            podcastSV.bottomAnchor.constraint(equalTo: podcastBtn.contentView.bottomAnchor, constant: 0).isActive = true
            podcastSV.heightAnchor.constraint(equalToConstant: podcastBtn.frame.height - 10).isActive = true
            
            cell.addSubview(podcastBtn)
            
            podcastBtn.addTarget(self, action: #selector(podcastIntent), for: .touchUpInside)
            
            
            let guideBtn = CustomPressableButton()
            
            guideBtn.colors = .init(button: btnShadow, shadow: btnColor)
            guideBtn.shadowHeight = 4
            guideBtn.cornerRadius = 8
            guideBtn.frame = CGRect(x: (screenWidth/2) + 2.5, y: 10, width: (screenWidth / 2) - 7.5, height: rowHeight)
            
            let guideSV = UIStackView(frame: frameRight)
            guideSV.translatesAutoresizingMaskIntoConstraints = false
            guideSV.axis = .vertical
            
            let guideImage = UIImage(named: "guide_icon")
            let guideIcon = UIImageView(image: UIImage(named: "guide_icon"))
            guideIcon.frame = CGRect(x: 0, y: 0, width: 30, height: 30)
            guideIcon.image = guideImage?.maskWithColor(color: UIColor.white)
            guideIcon.contentMode = .scaleAspectFit
            guideIcon.layer.masksToBounds = true
            guideIcon.translatesAutoresizingMaskIntoConstraints = false
            
            guideIcon.heightAnchor.constraint(equalToConstant: 70).isActive = true
            guideIcon.widthAnchor.constraint(equalToConstant: 100).isActive = true
            
            let guideTitleArabic = UILabel()
            guideTitleArabic.text = "دليل البرنامج"
            guideTitleArabic.textColor = UIColor.white
            guideTitleArabic.textAlignment = .center
            guideTitleArabic.font = UIFont(name: "Hacen Extender X4 Super Fit", size: 20)
            
            let guideTitleEnglish = UILabel()
            guideTitleEnglish.text = "User Guide"
            guideTitleEnglish.textColor = UIColor.white
            guideTitleEnglish.textAlignment = .center
            guideTitleEnglish.font = UIFont(name: "Arial", size: 15)
            
            guideSV.addArrangedSubview(guideIcon)
            guideSV.addArrangedSubview(guideTitleArabic)
            guideSV.addArrangedSubview(guideTitleEnglish)
            
            guideBtn.contentView.addSubview(guideSV)
            
            guideSV.leftAnchor.constraint(equalTo: guideBtn.contentView.leftAnchor, constant: 0).isActive = true
            guideSV.rightAnchor.constraint(equalTo: guideBtn.contentView.rightAnchor, constant: 0).isActive = true
            guideSV.bottomAnchor.constraint(equalTo: guideBtn.contentView.bottomAnchor, constant: 0).isActive = true
            guideSV.heightAnchor.constraint(equalToConstant: guideBtn.frame.height - 10).isActive = true
            
            cell.addSubview(guideBtn)
            
            guideBtn.addTarget(self, action: #selector(guideIntent), for: .touchUpInside)
            
            
            break
            
        case 3:
            
            let libraryBtn = CustomPressableButton()
            
            libraryBtn.colors = .init(button: btnShadow, shadow: btnColor)
            libraryBtn.shadowHeight = 4
            libraryBtn.cornerRadius = 8
            libraryBtn.frame = CGRect(x: 5, y: 10, width: (screenWidth / 2) - 7.5, height: rowHeight)
            
            let librarySV = UIStackView(frame: frameLeft)
            librarySV.translatesAutoresizingMaskIntoConstraints = false
            librarySV.axis = .vertical
            
            let libraryImage = UIImage(named: "library_icon")
            let libraryIcon = UIImageView(image: UIImage(named: "library_icon"))
            libraryIcon.frame = CGRect(x: 0, y: 0, width: 30, height: 30)
            libraryIcon.image = libraryImage?.maskWithColor(color: UIColor.white)
            libraryIcon.contentMode = .scaleAspectFit
            libraryIcon.layer.masksToBounds = true
            libraryIcon.translatesAutoresizingMaskIntoConstraints = false
            
            libraryIcon.heightAnchor.constraint(equalToConstant: 70).isActive = true
            libraryIcon.widthAnchor.constraint(equalToConstant: 100).isActive = true
            
            let libraryTitleArabic = UILabel()
            libraryTitleArabic.text = "مكتبة عامة"
            libraryTitleArabic.textColor = UIColor.white
            libraryTitleArabic.textAlignment = .center
            libraryTitleArabic.font = UIFont(name: "Hacen Extender X4 Super Fit", size: 19)
            
            let libraryTitleEnglish = UILabel()
            libraryTitleEnglish.text = "Library"
            libraryTitleEnglish.textColor = UIColor.white
            libraryTitleEnglish.textAlignment = .center
            libraryTitleEnglish.font = UIFont(name: "Arial", size: 15)
            
            librarySV.addArrangedSubview(libraryIcon)
            librarySV.addArrangedSubview(libraryTitleArabic)
            librarySV.addArrangedSubview(libraryTitleEnglish)
            
            libraryBtn.contentView.addSubview(librarySV)
            
            
            librarySV.leftAnchor.constraint(equalTo: libraryBtn.contentView.leftAnchor, constant: 0).isActive = true
            librarySV.rightAnchor.constraint(equalTo: libraryBtn.contentView.rightAnchor, constant: 0).isActive = true
            librarySV.bottomAnchor.constraint(equalTo: libraryBtn.contentView.bottomAnchor, constant: 0).isActive = true
            librarySV.heightAnchor.constraint(equalToConstant: libraryBtn.frame.height - 10).isActive = true
            
            cell.addSubview(libraryBtn)
            
            libraryBtn.addTarget(self, action: #selector(libraryIntent), for: .touchUpInside)
            
            
            let articlesBtn = CustomPressableButton()
            
            articlesBtn.colors = .init(button: btnShadow, shadow: btnColor)
            articlesBtn.shadowHeight = 4
            articlesBtn.cornerRadius = 8
            articlesBtn.frame = CGRect(x: (screenWidth/2) + 2.5, y: 10, width: (screenWidth / 2) - 7.5, height: rowHeight)
            
            let articlesSV = UIStackView(frame: frameRight)
            articlesSV.translatesAutoresizingMaskIntoConstraints = false
            articlesSV.axis = .vertical
            
            let articlesImage = UIImage(named: "articles_icon")
            let articlesIcon = UIImageView(image: UIImage(named: "articles_icon"))
            articlesIcon.frame = CGRect(x: 0, y: 0, width: 30, height: 30)
            articlesIcon.image = articlesImage?.maskWithColor(color: UIColor.white)
            articlesIcon.contentMode = .scaleAspectFit
            articlesIcon.layer.masksToBounds = true
            articlesIcon.translatesAutoresizingMaskIntoConstraints = false
            
            articlesIcon.heightAnchor.constraint(equalToConstant: 70).isActive = true
            articlesIcon.widthAnchor.constraint(equalToConstant: 100).isActive = true
            
            let articlesTitleArabic = UILabel()
            articlesTitleArabic.text = "مقالات"
            articlesTitleArabic.textColor = UIColor.white
            articlesTitleArabic.textAlignment = .center
            articlesTitleArabic.font = UIFont(name: "Hacen Extender X4 Super Fit", size: 19)
            
            let articlesTitleEnglish = UILabel()
            articlesTitleEnglish.text = "Articles"
            articlesTitleEnglish.textColor = UIColor.white
            articlesTitleEnglish.textAlignment = .center
            articlesTitleEnglish.font = UIFont(name: "Arial", size: 15)
            
            articlesSV.addArrangedSubview(articlesIcon)
            articlesSV.addArrangedSubview(articlesTitleArabic)
            articlesSV.addArrangedSubview(articlesTitleEnglish)
            
            articlesBtn.contentView.addSubview(articlesSV)
            
            
            articlesSV.leftAnchor.constraint(equalTo: articlesBtn.contentView.leftAnchor, constant: 0).isActive = true
            articlesSV.rightAnchor.constraint(equalTo: articlesBtn.contentView.rightAnchor, constant: 0).isActive = true
            articlesSV.bottomAnchor.constraint(equalTo: articlesBtn.contentView.bottomAnchor, constant: 0).isActive = true
            articlesSV.heightAnchor.constraint(equalToConstant: articlesBtn.frame.height - 10).isActive = true
            
            cell.addSubview(articlesBtn)
            
            articlesBtn.addTarget(self, action: #selector(articlesIntent), for: .touchUpInside)
            
            break
            
        case 4:
            
            let schoolBtn = CustomPressableButton()
            
            schoolBtn.colors = .init(button: btnShadow, shadow: btnColor)
            schoolBtn.shadowHeight = 4
            schoolBtn.cornerRadius = 8
            schoolBtn.frame = CGRect(x: 5, y: 10, width: (screenWidth / 2) - 7.5, height: rowHeight)
            
            let schoolSV = UIStackView(frame: frameLeft)
            schoolSV.translatesAutoresizingMaskIntoConstraints = false
            schoolSV.axis = .vertical
            
            let schoolImage = UIImage(named: "school_icon")
            let schoolIcon = UIImageView(image: UIImage(named: "school_icon"))
            schoolIcon.frame = CGRect(x: 0, y: 0, width: 30, height: 30)
            schoolIcon.image = schoolImage?.maskWithColor(color: UIColor.white)
            schoolIcon.contentMode = .scaleAspectFit
            schoolIcon.layer.masksToBounds = true
            schoolIcon.translatesAutoresizingMaskIntoConstraints = false
            
            schoolIcon.heightAnchor.constraint(equalToConstant: 70).isActive = true
            schoolIcon.widthAnchor.constraint(equalToConstant: 100).isActive = true
            
            let schoolTitleArabic = UILabel()
            schoolTitleArabic.text = "مدرسة معاكم"
            schoolTitleArabic.textColor = UIColor.white
            schoolTitleArabic.textAlignment = .center
            schoolTitleArabic.font = UIFont(name: "Hacen Extender X4 Super Fit", size: 19)
            
            let schoolTitleEnglish = UILabel()
            schoolTitleEnglish.text = "School"
            schoolTitleEnglish.textColor = UIColor.white
            schoolTitleEnglish.textAlignment = .center
            schoolTitleEnglish.font = UIFont(name: "Arial", size: 15)
            
            schoolSV.addArrangedSubview(schoolIcon)
            schoolSV.addArrangedSubview(schoolTitleArabic)
            schoolSV.addArrangedSubview(schoolTitleEnglish)
            
            schoolBtn.contentView.addSubview(schoolSV)
            
            schoolSV.leftAnchor.constraint(equalTo: schoolBtn.contentView.leftAnchor, constant: 0).isActive = true
            schoolSV.rightAnchor.constraint(equalTo: schoolBtn.contentView.rightAnchor, constant: 0).isActive = true
            schoolSV.bottomAnchor.constraint(equalTo: schoolBtn.contentView.bottomAnchor, constant: 0).isActive = true
            schoolSV.heightAnchor.constraint(equalToConstant: schoolBtn.frame.height - 10).isActive = true
            
            cell.addSubview(schoolBtn)
            
            schoolBtn.addTarget(self, action: #selector(schoolIntent), for: .touchUpInside)
            
            
            let videosBtn = CustomPressableButton()
            
            videosBtn.colors = .init(button: btnShadow, shadow: btnColor)
            videosBtn.shadowHeight = 4
            videosBtn.cornerRadius = 8
            videosBtn.frame = CGRect(x: (screenWidth/2) + 2.5, y: 10, width: (screenWidth / 2) - 7.5, height: rowHeight)
            
            let videosSV = UIStackView(frame: frameLeft)
            videosSV.translatesAutoresizingMaskIntoConstraints = false
            videosSV.axis = .vertical
            
            let videosImage = UIImage(named: "videos_icon")
            let videosIcon = UIImageView(image: UIImage(named: "videos_icon"))
            videosIcon.frame = CGRect(x: 0, y: 0, width: 30, height: 30)
            videosIcon.image = videosImage?.maskWithColor(color: UIColor.white)
            videosIcon.contentMode = .scaleAspectFit
            videosIcon.layer.masksToBounds = true
            videosIcon.translatesAutoresizingMaskIntoConstraints = false
            
            videosIcon.heightAnchor.constraint(equalToConstant: 70).isActive = true
            videosIcon.widthAnchor.constraint(equalToConstant: 100).isActive = true
            
            let videosTitleArabic = UILabel()
            videosTitleArabic.text = "فيديوهات"
            videosTitleArabic.textColor = UIColor.white
            videosTitleArabic.textAlignment = .center
            videosTitleArabic.font = UIFont(name: "Hacen Extender X4 Super Fit", size: 19)
            
            let videosTitleEnglish = UILabel()
            videosTitleEnglish.text = "Videos"
            videosTitleEnglish.textColor = UIColor.white
            videosTitleEnglish.textAlignment = .center
            videosTitleEnglish.font = UIFont(name: "Arial", size: 15)
            
            videosSV.addArrangedSubview(videosIcon)
            videosSV.addArrangedSubview(videosTitleArabic)
            videosSV.addArrangedSubview(videosTitleEnglish)
            
            videosBtn.contentView.addSubview(videosSV)
            
            
            videosSV.leftAnchor.constraint(equalTo: videosBtn.contentView.leftAnchor, constant: 0).isActive = true
            videosSV.rightAnchor.constraint(equalTo: videosBtn.contentView.rightAnchor, constant: 0).isActive = true
            videosSV.bottomAnchor.constraint(equalTo: videosBtn.contentView.bottomAnchor, constant: 0).isActive = true
            videosSV.heightAnchor.constraint(equalToConstant: videosBtn.frame.height - 10).isActive = true
            
            cell.addSubview(videosBtn)
            
            videosBtn.addTarget(self, action: #selector(videosIntent), for: .touchUpInside)
            
            
            break
        case 5:
            
            let fbPBtn = PressableButton()
            let smsPBtn = PressableButton()
            let emailPBtn = PressableButton()
            
            fbPBtn.colors = .init(button: btnShadow, shadow: btnColor)
            fbPBtn.shadowHeight = 3
            fbPBtn.cornerRadius = 8
            fbPBtn.depth = 0.5
            fbPBtn.frame = CGRect(x: 5, y: 10, width: (screenWidth / 3) - 7.5, height: 34)
            fbPBtn.setTitle("Facebook", for: .normal)
            fbPBtn.setTitleColor(UIColor.white, for: .normal)
            
            smsPBtn.colors = .init(button: btnShadow, shadow: btnColor)
            smsPBtn.shadowHeight = 3
            smsPBtn.cornerRadius = 8
            smsPBtn.depth = 0.5
            smsPBtn.frame = CGRect(x: (screenWidth / 3) + 2.5, y: 10, width: (screenWidth / 3) - 5, height: 34)
            smsPBtn.setTitle("Message", for: .normal)
            smsPBtn.setTitleColor(UIColor.white, for: .normal)
            
            emailPBtn.colors = .init(button: btnShadow, shadow: btnColor)
            emailPBtn.shadowHeight = 3
            emailPBtn.cornerRadius = 8
            emailPBtn.depth = 0.5
            emailPBtn.frame = CGRect(x: (2 * screenWidth / 3) + 2.5, y: 10, width: (screenWidth / 3) - 7.5, height: 34)
            emailPBtn.setTitle("Email", for: .normal)
            emailPBtn.setTitleColor(UIColor.white, for: .normal)
            
            cell.addSubview(fbPBtn)
            cell.addSubview(smsPBtn)
            cell.addSubview(emailPBtn)
            
            fbPBtn.addTarget(self, action: #selector(fbIntent), for: .touchUpInside)
            smsPBtn.addTarget(self, action: #selector(smsIntent), for: .touchUpInside)
            emailPBtn.addTarget(self, action: #selector(emailIntent), for: .touchUpInside)
            
            break
        default:
            break
        }
        
        return cell
    }
 
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        
        switch indexPath.row {
        case 1:
            return 44
        case 5:
            return 44
        default:
            return 140
        }
    }
}

extension HomeTVC: MFMessageComposeViewControllerDelegate, MFMailComposeViewControllerDelegate{
    
    func smsIntent(){
        if (MFMessageComposeViewController.canSendText()) {
            let controller = MFMessageComposeViewController()
            controller.body = ""
            controller.recipients = ["+201119229100"]
            controller.messageComposeDelegate = self
            self.present(controller, animated: true, completion: nil)
        }
    }
    
    func emailIntent() {
        if MFMailComposeViewController.canSendMail() {
            let mail = MFMailComposeViewController()
            mail.mailComposeDelegate = self;
            mail.setCcRecipients(["info@ma33a.com"])
            mail.setSubject("Ma33a App")
            mail.setMessageBody("", isHTML: false)
            self.present(mail, animated: true, completion: nil)
        }
    }
    
    func messageComposeViewController(_ controller: MFMessageComposeViewController, didFinishWith result: MessageComposeResult) {
        //... handle sms screen actions
        self.dismiss(animated: true, completion: nil)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        self.navigationController?.isNavigationBarHidden = false
    }
    
    func mailComposeController(_ controller: MFMailComposeViewController, didFinishWith result: MFMailComposeResult, error: Error?) {
        controller.dismiss(animated: true, completion: nil)
    }

}




