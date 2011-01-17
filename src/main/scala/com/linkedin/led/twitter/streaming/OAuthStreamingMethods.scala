package com.linkedin.led.twitter.streaming

import scala.collection.mutable.ArrayBuffer
import org.apache.commons.httpclient.NameValuePair
import org.apache.commons.httpclient.Header
import org.apache.commons.httpclient.methods.{PostMethod, GetMethod}

trait OAuthStreamingMethods extends StreamingMethods {

  override def buildGet(url: String, params: ArrayBuffer[NameValuePair]) : GetMethod = {
    val get = super.buildGet(url, params)
    get.addRequestHeader(new Header("Authorization", generateSignature(url, params)))
    return get
  }

  override def buildPost(url: String, params: ArrayBuffer[NameValuePair]) : PostMethod = {
    val post = super.buildPost(url, params)
    post.addRequestHeader(new Header("Authorization", generateSignature(url, params)))
    return post
  }

  def generateSignature (url : String, params : ArrayBuffer[NameValuePair]) : String = {
    "no signature yet"
  }

}