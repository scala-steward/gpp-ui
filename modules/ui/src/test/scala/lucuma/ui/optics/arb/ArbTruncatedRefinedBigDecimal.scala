// Copyright (c) 2016-2021 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.ui.optics.arb

import eu.timepit.refined.api.Refined
import eu.timepit.refined.scalacheck._
import eu.timepit.refined.scalacheck.numeric.intervalClosedArbitrary
import eu.timepit.refined.numeric.Interval
import lucuma.ui.optics.TruncatedRefinedBigDecimal
import org.scalacheck._
import org.scalacheck.Arbitrary._

trait ArbTruncatedRefinedBigDecimal {
  val OneBD   = BigDecimal(1.0)
  val ThreeBD = BigDecimal(3.0)
  type OneToThree    = Interval.Closed[OneBD.type, ThreeBD.type]
  type BigOneToThree = BigDecimal Refined OneToThree

  implicit val arbClosed: Arbitrary[BigOneToThree] = intervalClosedArbitrary

  implicit val arbTruncRefinedBD = Arbitrary[TruncatedRefinedBigDecimal[OneToThree, 1]] {
    arbitrary[BigOneToThree].map(TruncatedRefinedBigDecimal[OneToThree, 1](_).get)
  }

  implicit def cogTruncRefinedBD: Cogen[TruncatedRefinedBigDecimal[OneToThree, 1]] =
    Cogen[BigOneToThree].contramap(trbd => trbd.value)
  // Cogen[(BigOneToThree, Int)].contramap(trbd => (trbd.value, trbd.decimals))
}

object ArbTruncatedRefinedBigDecimal extends ArbTruncatedRefinedBigDecimal
