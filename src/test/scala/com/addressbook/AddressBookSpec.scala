package com.addressbook

import org.scalatest.{FlatSpec, Matchers}


/**
  * Created by shailesh on 26/03/2017.
  */
class AddressBookSpec extends FlatSpec with Matchers{

  "A text file" should  "be load as List of String" in {

    AddressBook.readFile("AddressBook") shouldBe a [List[_]]

  }

  "A Profile Model" should "be populated from List of String" in {

    val strList = List("John Patton , Male, 01/11/67")
    AddressBook.getProfiles(strList) shouldBe a [List[_]]

  }



}


