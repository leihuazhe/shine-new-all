package com.maple.shine.versions.java12;

enum Incomes {
    Good,
    Regular,
    Bad
}

public class SwitchExpressionExample {
    public static void main(String... args) {
        compatibleWithOldVersion(7);
        compatibleWithOldVersion(100);

        longVersion(Incomes.Bad);
        shortVersion();

        switchWithReturn();

        inferringType();
    }

    /*public static void compatibleWithOldVersion() {
        int luckNumber = 7;

        // we can use the old syntax (case:)
        var wasLucky = switch (luckNumber) {
            // here the fall-through happens (if there is no break or return the next case will be executed)
            case 7:
                break false;  // break was changed to yield in Java 13
            case 13:
                break true;
            // here we must provide a default case (compiler checks exhaustiveness)
            default:
                throw new IllegalArgumentException("Wrong number");
        }; // switch expression needs ;

        System.out.println("\nWas I lucky? " + wasLucky);
    }*/

    public static void compatibleWithOldVersion(int luckNumber) {
        // we can use the old syntax (case:)
        var wasLucky = switch (luckNumber) {
            // here the fall-through happens (if there is no break or return the next case will be executed)
            case 7:
                yield false;  // break was changed to yield in Java 13
            case 13:
                yield true;
                // here we must provide a default case (compiler checks exhaustiveness)
            default:
                throw new IllegalArgumentException("Wrong number");
        }; // switch expression needs ;

        var wasLuckyV2 = switch (luckNumber) {
            // here the fall-through happens (if there is no break or return the next case will be executed)
            case 7 -> false;
            case 13 -> true;
            default -> throw new IllegalArgumentException("Wrong number");
        }; // switch expression needs ;

        System.out.println("\nWas I lucky? " + wasLucky);
    }

    /**
     * 以下代码只能在 java 12 中运行, java 13 不可以运行，使用 yield 替代了 break
     */
    /*public static void longVersion() {
        var mood = Incomes.Good;

        // arrow syntax with no fall-through (only the arrow block will be executed)
        var shouldIGo = switch (mood) {
            // accepts multiple case labels
            case Good, Regular -> {
                break true;
            }
            case Bad -> {
                break false;
            }
            // there is no need to use default (we covered all possible cases)
        };

        System.out.println("\nShould I go? " + shouldIGo);
    }*/

    /**
     * 以下代码可以运行
     */
    public static void longVersion(Incomes mood) {
        // arrow syntax with no fall-through (only the arrow block will be executed)
        var shouldIGo = switch (mood) {
            // accepts multiple case labels
            case Good, Regular -> {
                yield true;
            }
            case Bad -> {
                yield false;
            }
            // there is no need to use default (we covered all possible cases)
        };
        System.out.println("\nShould I go? " + shouldIGo);
    }

    public static void shortVersion() {
        var mood = Incomes.Good;

        // cool syntax with no fall-through (only the arrow block will be executed)
        var shouldIGo = switch (mood) {
            // accepts multiple case labels
            case Good, Regular -> true;
            case Bad -> false;
            // there is no need to use default (we covered all possible cases)
        };

        System.out.println("\nShould I go? " + shouldIGo);
    }

    public static void switchWithReturn() {
        var mood = Incomes.Bad;

        // used as statement (there is no assign)
        switch (mood) {
            case Good -> {
                var easy = true;
                System.out.println("\nKeep working");
            }
            case Regular, Bad -> {
                // we can now declare vars with same name (each arrow has its own scope)
                var easy = true;
                System.out.println("\nGo home");
                /*
                 * we can only return inside a block and when switch
                 * is used as statement
                 */
            }
        }
    }

    public static void inferringType() {
        var mood = Incomes.Good;

        // compiler infers the most specific supertype
        // of String and IllegalArgumentException is Serializable
        // Serializable whichType // we can only use its supertype
        var whichType = switch (mood) {
            case Good -> "All right";
            default -> new IllegalArgumentException("Not right");
        };

        // won't work
        // System.out.println("What is the length? " + whichType.length());
    }
}