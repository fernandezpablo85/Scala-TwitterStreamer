package com.linkedin.led.twitter.streaming.oauth

import com.linkedin.led.twitter.streaming._

import scala.collection.mutable.ArrayBuffer
import org.apache.commons.httpclient.NameValuePair
import org.apache.commons.httpclient.HttpMethod
import org.apache.commons.httpclient.methods._
import com.linkedin.led.twitter.Config

trait OAuthStreamingMethods extends StreamingMethods {
  
  val validToken = "oauth_token=(.*)&oauth_token_secret=(.*)".r
  
  var requestToken: Token = null;
  var accessToken: Token = null;
  var verifier: String = null;
  
  def getAccessToken(): Token = {
    assume(requestToken != null && verifier != null);
    return null;
  }
  
  def getRequestToken(): Token = {
    val url = Config.readString("requestTokenEndpoint")
    val request = super.buildGet(url, new ArrayBuffer[NameValuePair]())
    request.addRequestHeader("Authorization", generateSignature("GET", url))
    executeRequest(request)
    parseToken(request.getResponseBodyAsString())
  }

  override def buildGet (url: String, params: ArrayBuffer[NameValuePair]): GetMethod = {
    val get = super.buildGet(url, params)
    get.addRequestHeader("Authorization", generateSignature("GET", url, params))
    return get
  }

  override def buildPost (url: String, params: ArrayBuffer[NameValuePair]): PostMethod = {
    val post = super.buildPost(url, params)
    post.addRequestHeader("Authorization", generateSignature("POST", url, params))
    return post
  }

  def generateSignature (verb: String, url: String, params: ArrayBuffer[NameValuePair]): String = {
    "no signature yet"
  }
  
  def generateSignature (verb: String, url: String): String = {
    generateSignature(verb, url, new ArrayBuffer[NameValuePair]())
  }
  
  def executeRequest (method: HttpMethod, gzip: Boolean = false);
  
  def parseToken (responseBody: String): Token = {
    responseBody match {
      case validToken(token, secret) => 
        new Token(token, secret)
      case _ =>
        throw new IllegalStateException("token not found in response: " + responseBody)
    }
  }

}