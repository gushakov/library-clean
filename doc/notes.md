### Things we've learned

1. It's important to be as expressive as possible when writing tests. See `com.github.libraryclean.core.model.LibraryDsl`
for a convenient DSL for our domain models.
2. We need to be careful when asserting for errors: make sure we check for exact (sub)type of the error thrown.