//
//  SliderTVcell.swift
//  WithYou
//
//  Created by Mamdouh El Nakeeb on 7/5/17.
//  Copyright © 2017 ma33a. All rights reserved.
//

import UIKit

class SliderTVcell: UITableViewCell {

    @IBOutlet weak var sliderCV: UICollectionView!
    
    
    var sourceArray: [String] = ["slide1.jpg", "slide2.jpg", "slide3.jpg", "slide4.jpg", "slide5.jpg"]
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        
        
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}


// MARK: - Collection View Data source and Delegate
extension SliderTVcell: UICollectionViewDataSource,UICollectionViewDelegate {
    
    
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 5
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "SliderCell", for: indexPath) as! SliderCVcell
        
        var imgUrl = "http://ma33a.com/app/slides/"
        
        imgUrl += sourceArray[indexPath.row]
        
        print(imgUrl)
        
        cell.sliderCellIV.sd_setImage(with: URL(string: imgUrl), placeholderImage: UIImage(named: "slidertemp"))
        
        
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        
        switch indexPath.row {
        case 0:
            UIApplication.tryURL(urls: [
                "https://ma33a.com/slide1"
                ])
            break
        case 1:
            UIApplication.tryURL(urls: [
                "https://ma33a.com/slide2"
                ])
            break
            
        case 2:
            UIApplication.tryURL(urls: [
                "https://ma33a.com/slide3"
                ])
            break
            
        case 3:
            UIApplication.tryURL(urls: [
                "https://ma33a.com/slide4"
                ])
            break
            
        case 4:
            UIApplication.tryURL(urls: [
                "https://ma33a.com/slide5"
                ])
            break
        default:
            break
        }
    }
    
    
}
