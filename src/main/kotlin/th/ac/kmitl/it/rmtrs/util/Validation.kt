package th.ac.kmitl.it.rmtrs.util

import java.time.LocalDate

fun validateDate(dateStr: String): LocalDate = if(dateStr == "none") LocalDate.now() else dateStr.toLocalDate()