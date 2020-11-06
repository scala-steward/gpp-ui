// Copyright (c) 2016-2020 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.ui

import eu.timepit.refined.api.Refined
import eu.timepit.refined.char.UpperCase
import eu.timepit.refined.collection.NonEmpty
import eu.timepit.refined.api.RefinedTypeOps
import eu.timepit.refined.collection.Forall
import eu.timepit.refined.boolean.And

/**
 * Convenience refined definitions.
 */
package object refined {
  type UpperNES = String Refined And[NonEmpty, Forall[UpperCase]]
  object UpperNES extends RefinedTypeOps[UpperNES, String]
}