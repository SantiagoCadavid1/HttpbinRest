Feature: Httpbin Post petition

  @Post

  Scenario Outline: Successfully made post petition to httpbin

    When I send a request to the endpoint "<endpoint>" with the key value "<value>"
    Then I Validate that the response code is "<code>"

    Examples:
      | endpoint | value | code |
      | /post    | value | 200  |
