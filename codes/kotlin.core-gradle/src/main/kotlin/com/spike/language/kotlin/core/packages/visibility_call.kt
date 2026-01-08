package com.spike.language.kotlin.core.packages


// can we extend non open class
// NO!
// class CallC1 : C1() {}

class CallC2 : C2() {}

// C3 is private and final
// class CallC3: C3() {}
// private class CallC4 : C3() {}

// cannot public extends internal class
//class CallC5 : C4() {}
private class CallC6 : C4() {}

internal class CallC7 : C4() {}