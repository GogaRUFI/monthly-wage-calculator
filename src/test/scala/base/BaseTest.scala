package base

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.mock.MockitoSugar
import org.scalatest.{FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
abstract class BaseTest extends FunSuite with Matchers with MockitoSugar
