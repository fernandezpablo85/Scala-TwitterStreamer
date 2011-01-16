import com.linkedin.led.twitter.streaming._

import org.scalatest._
import org.scalatest.matchers._

class BackOffSpec extends Spec with MustMatchers with BeforeAndAfterEach {

  var backOff: BackOff = null

  override def beforeEach() {
    backOff = BackOff(250, 16000)
  }

  describe("Back Off strategy") {

    it ("should set the correct backOffTime and capBackOffAt") {
      backOff.backOffTime must be === 250
      backOff.capBackOffAt must be === 16000
    }

    it ("should increment the back off time incrementally") {
      backOff.backOff
      backOff.backOffTime must be === 500
      backOff.backOff
      backOff.backOffTime must be === 1000
    }

    it ("should allow to reset the incremental back off time") {
      backOff.backOff
      backOff.backOffTime must be === 500
      backOff.reset()
      backOff.backOffTime must be === 0
    }
  }
}