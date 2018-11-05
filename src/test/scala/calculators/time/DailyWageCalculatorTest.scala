package calculators.time

import base.BaseTest
import calculators.wage.DailyWageCalculator
import models.Shift
import org.joda.time.LocalTime

class DailyWageCalculatorTest extends BaseTest {

	test("Calculates wage result for a time regular and evening result") {
		val calc = new DailyWageCalculator
		def ltime(h: Int, m: Int) = new LocalTime(h, m).getMillisOfDay

		val total = ltime(5,15)
		val evening = ltime(3,0)
		val over = ltime(0,0)

		val time = TimeResult(total,evening,over)


		val result = calc.calculate(time)

		result shouldEqual BigDecimal(22.76)
	}

	test("Calculates wage result for a time with over regular and evening result") {
		val calc = new DailyWageCalculator
		def ltime(h: Int, m: Int) = new LocalTime(h, m).getMillisOfDay

		val total = ltime(14,15)
		val evening = ltime(3,0)
		val over = ltime(6,15)

		val time = TimeResult(total,evening,over)


		val result = calc.calculate(time)

		result shouldEqual BigDecimal(70.20)
	}

}


