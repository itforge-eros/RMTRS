package th.ac.kmitl.it.rmtrs.definition

enum class Rate(val meaning: String, val description: String) {
    G("G – General Audiences", "All ages admitted. Nothing that would offend parents for viewing by children."),
    PG("PG – Parental Guidance Suggested", "Some material may not be suitable for children. Parents urged to give \"parental guidance\". May contain some material parents might not like for their young children."),
    PG_13("PG-13 – Parents Strongly Cautioned", "Some material may be inappropriate for children under 13. Parents are urged to be cautious. Some material may be inappropriate for pre-teenagers."),
    R("R – Restricted", "Under 17 requires accompanying parent or adult guardian. Contains some adult material. Parents are urged to learn more about the film before taking their young children with them."),
    NC_17("C-17 – Adults Only", "No One 17 and Under Admitted. Clearly adult. Children are not admitted.")
}