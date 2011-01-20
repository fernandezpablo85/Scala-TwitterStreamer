import org.scalatest._
import org.scalatest.matchers._
import scala.collection.mutable._
import org.apache.commons.httpclient._

import com.linkedin.led.twitter.streaming._
import com.linkedin.led.twitter.streaming.oauth._

class OAuthStreamingMethodsSpec extends Spec with MustMatchers with BeforeAndAfterEach {
  
  var client: OAuthStreamingMethods = null
  
  override def beforeEach() 
  {
    client = new StreamingClient ("username", "password", new OutputStreamProcessor ) with OAuthStreamingMethods
  }

  describe ("OAuth Streaming Client") 
  {
    it ("Should retrieve a valid request token")
    {
      val token = client.getRequestToken
      token.token.length must be > (0)
      token.secret.length must be > (0)
    }

    it ("Should generate a correct signature") 
    {
      client.generateSignature("GET", "http://www.example.com", null) must be === "no signature yet"
    }

  }
}