## Library DDD example using Clean DDD

## About

This a revisit of a popular implementation of Library domain originally available from
[ddd-by-examples/library](https://github.com/ddd-by-examples/library). The idea is to implement the similar domain but
using Clean DDD approach this time. Having implemented similar domain will allow us to compare and contrast more easily
two approaches. Here are the main differences from the original architecture and implementation.

- Clean DDD approach
- Use case centric design
- Less reliance on "Domain Events" pattern

Here is the description for the Library domain, reprinted here from
[the original source](https://github.com/ddd-by-examples/library#domain-description) for convenience:

> A public library allows patrons to place books on hold at its various library branches. Available books can be placed
> on hold only by one patron at any given point in time. Books are either circulating or restricted, and can have
> retrieval or usage fees. A restricted book can only be held by a researcher patron. A regular patron is limited
> to five holds at any given moment, while a researcher patron is allowed an unlimited number of holds.
> An open-ended book hold is active until the patron checks out the book, at which time it is completed.
> A closed-ended book hold that is not completed within a fixed number of days after it was requested will expire.
> This check is done at the beginning of a day by taking a look at daily sheet with expiring holds.
> Only a researcher patron can request an open-ended hold duration. Any patron with more than two overdue checkouts
> at a library branch will get a rejection if trying a hold at that same library branch. A book can be checked out
> for up to 60 days. Check for overdue checkouts is done by taking a look at daily sheet with overdue checkouts.
> Patron interacts with his/her current holds, checkouts, etc. by taking a look at patron profile. Patron profile
> looks like a daily sheet, but the information there is limited to one patron and is not necessarily daily.
> Currently a patron can see current holds (not canceled nor expired) and current checkouts (including overdue).
> Also, he/she is able to hold a book and cancel a hold.
>
> How actually a patron knows which books are there to lend? Library has its catalogue of books where books are
> added together with their specific instances. A specific book instance of a book can be added only if there is
> book with matching ISBN already in the catalogue. Book must have non-empty title and price. At the time of adding
> an instance we decide whether it will be Circulating or Restricted. This enables us to have book with same ISBN as
> circulated and restricted at the same time (for instance, there is a book signed by the author that we want to keep
> as Restricted)

## Our domain changes

We are introducing some changes to the domain:

- There is only one library branch.
- We only deal with active (open) holds and checkouts.
- Patron places a hold on a book by specifying the ISBN of the corresponding catalog entry. Once there is any
  _book instance_ available for checkout, it will automatically be registered as a checkout for the patron. If the only
  books for a given catalog entry are restricted, this catalog entry can only be placed on hold by a researcher patron.
- Holds are issued on first-come first-served basis and no two or more holds can be issued for a book with the one and
  the same ISBN.

## Copyright notice and disclaimer

The code in this repository is heavily inspired by the original code in
[ddd-by-examples/library](https://github.com/ddd-by-examples/library) repository. Due to the fact that the same domain
is used by this implementation, some code may appear very similar to the corresponding code in the original repository.

Here is the link to [the original licence](https://github.com/ddd-by-examples/library/blob/master/LICENSE)
(MIT License, 2019 Jakub Pilimon).

## References

1. [GitHub, ddd-by-examples/library](https://github.com/ddd-by-examples/library)
2. [Clean DDD](https://medium.com/unil-ci-software-engineering/clean-domain-driven-design-2236f5430a05)
3. [Cargo Clean](https://medium.com/unil-ci-software-engineering/revisiting-cargo-tracking-application-using-clean-ddd-4ed16c0e6ae1)