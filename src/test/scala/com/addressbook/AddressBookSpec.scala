package com.addressbook

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.scalatest.{FlatSpec, Matchers}


/**
  * Created by shailesh on 26/03/2017.
  */
class AddressBookSpec extends FlatSpec with Matchers{

  "A text file" should  "be load as List of String" in {

    AddressBook.readFile("AddressBook") shouldBe a [List[_]]

  }

  "A Profile Model" should "be populated from List of String" in {
    implicit def str2date(str: String) = DateTime.parse(str ,DateTimeFormat.forPattern("dd/MM/yy"))

    val strList = List("John Patton , Male, 01/11/67")

    val profiles = AddressBook.getProfiles(strList)

    profiles shouldBe a [List[_]]

    profiles.head.gender shouldBe "Male"
    profiles.head.name shouldBe "John Patton"
    profiles.head.dateOfBirth shouldBe  str2date("01/11/67")


  }



}


