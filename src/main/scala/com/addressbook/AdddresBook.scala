package com.addressbook

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

/**
  * Created by shailesh on 26/03/2017.
  */



trait AddressBook{


  /**
    *
    * @param name File to be loaded from resources
    * @return List[String]
    */
  def readFile(name:String) : List[String]={
    val bufferedSource = io.Source.fromInputStream(getClass().getClassLoader().getResourceAsStream(name))
     bufferedSource.getLines.toList
  }

  def getProfiles(stringList:List[String]):List[Profile]={

    implicit def str2date(str: String) = DateTime.parse(str ,DateTimeFormat.forPattern("dd/MM/yy"))

    stringList.map{
      line => {
        val cols = line.split(",").map(_.trim)
        Profile(cols(0),cols(1),cols(2))
      }
    }

  }

}

object AddressBook extends AddressBook