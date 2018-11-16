class HTTP_ERROR(Exception):
    def __init__(self, message):

        # Call the base class constructor with the parameters it needs
        super(HTTP_ERROR, self).__init__(message)