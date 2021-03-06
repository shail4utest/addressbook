package com.addressbook

import org.joda.time.{DateTime, Days}
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


  def getGenderCount(gender:String)(profiles:List[Profile]) : Int={
    profiles.count(_.gender==gender)
  }

  def getMaleCount(profiles:List[Profile]) : Int={
    getGenderCount("Male")(profiles)
  }


  def getOldestPerson(profiles:List[Profile]) : Profile={
      profiles.foldLeft(profiles.head){ (x,y) => if (x.dateOfBirth.isBefore(y.dateOfBirth)) x else y }
  }

  def getNoOfDaysBetweenTwoDates(date1:DateTime,date2:DateTime): Int = {
     if (date1.isBefore(date2)) {
       Days.daysBetween(date1, date2).getDays()
     }else {
       Days.daysBetween(date2, date1).getDays()
     }
  }


  def findProfile(name:String)(profiles:List[Profile]) : Option[Profile]={
    profiles.find(_.name.contains(name))
  }

}

object AddressBook extends AddressBook{

  def main(args: Array[String]) {
    val maleCount = getMaleCount(getProfiles(readFile("AddressBook")))

    println("****************************************************************************************")
    println("How many males are in the address book? " + maleCount)
    println("****************************************************************************************")

    val oldestPerson = getOldestPerson(getProfiles(readFile("AddressBook")))

    println("Who is the oldest person in the address book? " + oldestPerson.name)
    println("****************************************************************************************")

    val paul = findProfile("Paul")(getProfiles(readFile("AddressBook")))
    val bill = findProfile("Bill")(getProfiles(readFile("AddressBook")))
    val days = getNoOfDaysBetweenTwoDates(paul.get.dateOfBirth,bill.get.dateOfBirth)

    println("How many days older is Bill than Paul? " +days)

    println("****************************************************************************************")


    //println("[ " + args.mkString(", ") + " ] => £" + ShoppingCart.checkoutWithOffers(args))
  }

}