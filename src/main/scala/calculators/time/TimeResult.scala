package calculators.time

case class TimeResult(total: Long, evening: Long, over: Long) {
	def +(that: TimeResult) = {
		TimeResult(
			this.total + that.total,
			this.evening + that.evening,
			this.over + that.over
		)
	}
}

object TimeResult {
	val Zero = TimeResult(0,0,0)
}