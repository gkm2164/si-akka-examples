package com.smudi.satreci.messages

trait Message
case class Greet(name: String) extends Message
case class Bye(name: String) extends Message