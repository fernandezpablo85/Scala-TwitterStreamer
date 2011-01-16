import com.linkedin.led.twitter.streaming._

import scala.collection.mutable.ArrayBuffer
import org.apache.commons.httpclient._

import org.apache.commons.httpclient.methods._

import org.scalatest._
import org.scalatest.matchers.MustMatchers

class StreamingMethodsSpec extends Spec with MustMatchers with BeforeAndAfterEach {

  var client: StreamingClientSpecHelper = null
  var o: OutputStreamProcessor = null

  override def beforeEach() {
    o = new OutputStreamProcessor
    client = new StreamingClientSpecHelper("user", "password", o)
  }

  describe ("Streaming Methods") {

    it ("should build a Get request with specified params") {
      val params = new ArrayBuffer[String]
        params += "count=10"
      val request = client.buildGet("www.linkedin.com", params)
      request.getURI must be === new URI("www.linkedin.com?count=10")
    }

    it ("should build a post request with specified params") {
      val params = new ArrayBuffer[NameValuePair]
        params += new NameValuePair("follow", "linkedin,inapps,cool")
      val request = client.buildPost("www.linkedin.com", params)
      request.getURI must be === new URI("www.linkedin.com")
      request.getParameters.length must be === 1
      request.getParameter("follow") must be === new NameValuePair("follow", "linkedin,inapps,cool")
    }
  }
}