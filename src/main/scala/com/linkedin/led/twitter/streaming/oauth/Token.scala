package com.linkedin.led.twitter.streaming.oauth

class Token (val token: String, val secret: String) {
  
}

object Token {
  def Empty = new Token("", "")
}