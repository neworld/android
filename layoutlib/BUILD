load("//tools/base/bazel:bazel.bzl", "iml_module")

iml_module(
    name = "layoutlib",
    srcs = ["src"],
    resources = ["resources"],
    tags = ["managed"],
    visibility = ["//visibility:public"],
    exports = ["//tools/idea/.idea/libraries:layoutlib"],
    # do not sort: must match IML order
    deps = [
        "//tools/idea/.idea/libraries:layoutlib",
        "//tools/base/sdk-common:studio.sdk-common[module]",
        "//tools/idea/platform/annotations[module]",
        "//tools/adt/idea/common:android-common[module]",
        "//tools/idea/platform/platform-api[module]",
    ],
)
